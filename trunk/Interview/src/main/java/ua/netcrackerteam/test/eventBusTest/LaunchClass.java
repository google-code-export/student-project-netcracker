package ua.netcrackerteam.test.eventBusTest;

/**
 *
 */
public class LaunchClass {
    public static EventBus eventBus = EventBus.getInstance();
    static int i =0;

    private static class ClassWithView {
        public void userDidButtonClick() {
            eventBus.fireEvent(new Event(Event.EVENT_TYPE_CREATE_PDF));
        }
    }

    public static void main(String[] args) {

        FirstPDF c1 = new FirstPDF();
        SendMail c2 = new SendMail();
        DeleteFolders c3 = new DeleteFolders();
        eventBus.registerHandlerForEvent((EventHandler)c1);
        eventBus.registerHandlerForEvent((EventHandler)c2);
        eventBus.registerHandlerForEvent((EventHandler)c3);

        ClassWithView pseudoInteractionGenerator = new ClassWithView();
        pseudoInteractionGenerator.userDidButtonClick();

        i++;
        System.out.println(i);
    }
}
