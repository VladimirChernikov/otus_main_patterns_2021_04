package ru.otus.tanks.controller;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import ru.otus.core.controller.Command;
import ru.otus.tanks.adapters.FlowableAdapter;
import ru.otus.tanks.domain.GameObject;


@DisplayName("Обработка потока команд")
public class FlowableCommandTest {


    private Map<String, Object> flowProps;

    @BeforeEach
    void setUp(){
        flowProps = new HashMap<>();
        flowProps.put("FLOWABLE.TERMINATE",Boolean.FALSE);
        flowProps.put("FLOWABLE.TERMINATE_FORCE",Boolean.FALSE);
        flowProps.put("FLOWABLE.TERMINATED",Boolean.FALSE);
        var queue = new LinkedBlockingDeque<Command>();
        flowProps.put("FLOWABLE.QUEUE", queue);
    }

    @DisplayName("После команды старт поток запущен")
    @Test
    void shouldSpawnAnotherThreadAfterCommandStart() throws InterruptedException {
        //given
        Command command = mock(Command.class);
        final CountDownLatch latch = new CountDownLatch(1);
        doAnswer(
                a ->
                {
                    latch.countDown();
                    return null;
                }
                ).when(command).execute();

        //
        var flow = new FlowableAdapter( new GameObject( flowProps ) );
        flow.appendCommand(command);

        var flowableCommand = new FlowableCommand( flow );

        // when
        flowableCommand.execute();

        // then
        assertTimeout(Duration.ofSeconds(1), () -> latch.await());
        
    }


    @DisplayName("После команды hard stop, поток завершается")
    @Test
    void shouldStopImmediateAfterForceTermination() throws InterruptedException {
        //given
        Command command = mock(Command.class);
        Command anotherCommand = mock(Command.class);
        //
        var flow = new FlowableAdapter( new GameObject( flowProps ) );
        var terminateCommand = new TerminateForceFlowableCommand(flow);

        flow.appendCommand( command );
        flow.appendCommand( command );
        flow.appendCommand( terminateCommand );
        flow.appendCommand( anotherCommand );

        var flowableCommand = new FlowableCommand( flow );

        // when
        flowableCommand.execute();

        // then
        assertTimeout(Duration.ofSeconds(1), 
                () -> {
                    while ( !flow.isTerminated() )  { }
                }
                );
        verify( command, times(2) ).execute();
        verify( anotherCommand, times(0) ).execute();
    }

    @DisplayName("После команды soft stop, поток завершается только после того, как все задачи закончились")
    @Test
    void shouldSoftStopAfterTermination() throws InterruptedException {
        //given
        Command command = mock(Command.class);
        Command anotherCommand = mock(Command.class);
        //
        var flow = new FlowableAdapter( new GameObject( flowProps ) );
        var terminateCommand = new TerminateFlowableCommand(flow);

        flow.appendCommand( command );
        flow.appendCommand( command );
        flow.appendCommand( terminateCommand );
        flow.appendCommand( anotherCommand );
        flow.appendCommand( anotherCommand );
        flow.appendCommand( anotherCommand );

        var flowableCommand = new FlowableCommand( flow );

        // when
        flowableCommand.execute();

        // then
        assertTimeout(Duration.ofSeconds(1), 
                () -> {
                    while ( !flow.isTerminated() )  { }
                }
                );
        verify( command, times(2) ).execute();
        verify( anotherCommand, times(3) ).execute();
    }

}
