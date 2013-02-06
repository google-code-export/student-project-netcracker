package ua.netcrackerteam.test.eventBusTest;

import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class EventBus {
    private static List<EventHandler> listOfHandlers = new LinkedList<EventHandler>();
    private static EventBus instance = new EventBus();

    public static EventBus getInstance() {
        return instance;
    }
    public void registerHandlerForEvent(EventHandler handler) {
        //в идеале тут мы должны для каждого типа эвента иметь свой список
        listOfHandlers.add(handler);
    }

    public void fireEvent(Event event) {
        //а тут мы должны дергать все обработчики подписанные на этот конкретный тип события
        for (EventHandler handler: listOfHandlers) {
            //EventHandler obj = (EventHandler)handler;

            handler.handleEvent(event);
        }

    }
}
