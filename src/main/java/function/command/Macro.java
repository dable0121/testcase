package function.command;

import java.util.ArrayList;
import java.util.List;

public class Macro {
    private final List<Action> actions;

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Action action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Action::perform);
    }

    public static void main(String[] args) {
        Editor editor = new Editor() {
            @Override
            public void save() {
                System.out.println("saved");
            }

            @Override
            public void open() {
                System.out.println("opened");
            }

            @Override
            public void close() {
                System.out.println("closed");
            }
        };

        Macro macro = new Macro();
        macro.record(editor::save);
        macro.record(editor::open);
        macro.record(editor::close);
        macro.run();
    }


}
