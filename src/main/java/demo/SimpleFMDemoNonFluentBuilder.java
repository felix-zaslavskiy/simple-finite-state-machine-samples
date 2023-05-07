package demo;


import com.hexadevlabs.simplefsm.SimpleFSM;
import com.hexadevlabs.simplefsm.ProcessingData;
import com.hexadevlabs.simplefsm.State;



public class SimpleFMDemoNonFluentBuilder {
    public static void main(String[] args) {
        // Create states with processing steps
        State startState = new State("START", new Step1(), false);
        State step2State = new State("STEP2", new Step2(), true);
        State step3State = new State("STEP3", new Step3(), false);
        State endState = new State("END", new Step4(), false);

        // Define transitions
        startState.addTransition("STEP2", "STEP2");
        startState.addTransition("STEP3", "STEP3");

        step2State.addTransition("proceed", "END");
        step3State.addTransition("AUTO", "END");

        // Create simpleFSM and add states
        SimpleFSM simpleFSM = new SimpleFSM();
        simpleFSM.addState(startState);
        simpleFSM.addState(step2State);
        simpleFSM.addState(step3State);
        simpleFSM.addState(endState);
        simpleFSM.setTraceMode(true);

        // Start processing with initial data
        // Will go Start -> Step3 -> End
        ProcessingData data = new ProcessingData();
        data.set("value", 5);
        simpleFSM.start("START", data);

        simpleFSM.getTrace().print();

        System.out.println("State machine is active: " + simpleFSM.isStarted());

        // Output final result
        System.out.println("Final result: " + data.get("value"));

        // Second example
        simpleFSM = new SimpleFSM();
        simpleFSM.addState(startState);
        simpleFSM.addState(step2State);
        simpleFSM.addState(step3State);
        simpleFSM.addState(endState);
        simpleFSM.setTraceMode(true);

        // Start processing with initial data
        // Will go Start -> Step2 -> Wait -> Proceed -> End
        ProcessingData data2 = new ProcessingData();
        data2.set("value", 4);
        simpleFSM.start("START", data2);

        // Trigger external event
        simpleFSM.triggerEvent("proceed", data2);

        simpleFSM.getTrace().print();

        System.out.println("State machine is active: " + simpleFSM.isPaused());

        // Output final result
        System.out.println("Final result: " + data2.get("value"));
    }


}
