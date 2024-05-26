package CommandManage;

public class Invoker {
    Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run(){
        command.execute();
    }

}
