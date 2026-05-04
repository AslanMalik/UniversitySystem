/**
 * Entry point. Delegates to proj.TestAll for the code-review smoke tests,
 * or to proj.Tester for the full interactive system demo.
 *
 * To run tests:   set Main as run config  →  runs TestAll automatically.
 * To run the app: change the call below to  proj.Tester.main(args);
 */
public class Main {
    public static void main(String[] args) throws Exception {
        proj.TestAll.main(args);
        // proj.Tester.main(args);   // ← uncomment for interactive mode
    }
}
