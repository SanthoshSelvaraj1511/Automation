package cap.utilities;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cap.utilities.APIUtil.*;


public class CustomGherkinStepListener implements ConcurrentEventListener {

    public static LinkedHashMap<Object, Object> dataMapForTestStepStatus = new LinkedHashMap<>();

    private static List<String> dataMapForImageBytes = new ArrayList<>();
    private static StringBuilder imgLocatorForImageBytes = new StringBuilder();
    private static byte[] imgByte = null;


    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::afterScenarioComplete);
        publisher.registerHandlerFor(TestCaseStarted.class, this::beforeScenario);
        publisher.registerHandlerFor(TestStepFinished.class, this::afterGherkinStep);
        publisher.registerHandlerFor(TestStepStarted.class, this::beforeGherkinStep);
        publisher.registerHandlerFor(EmbedEvent.class, this.getEmbedEventHandler());

    }

    private void beforeGherkinStep(TestStepStarted event) {
        dataMapForImageBytes.clear();
    }

    protected EventHandler<EmbedEvent> getEmbedEventHandler() {
        return (event) -> {
            dataMapForImageBytes.add(event.getData().toString());
            imgLocatorForImageBytes.append(event.getData()).append(", ");
            imgByte = event.getData();
        };
    }


    // This is triggered when TestStep is finished
    private void afterGherkinStep(TestStepFinished event) {
        String stepName = "";
        String keyword = "";

        // We checks whether the event is from a hook or step
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();

            if (!(keyword.isEmpty())) {
                String strStep = keyword + stepName;
                String strStatus = event.getResult().getStatus().toString();
                dataMapForTestStepStatus.put(strStep, strStatus);
                try {
                    dataMapForTestStepStatus.put("Exception", event.getResult().getError().toString());
                } catch (Exception e) {
//                    System.out.println("Warning: Exception for Embedding GetError func.");
                }
            }
        } else if (event.getTestStep() instanceof HookTestStep) {
            try {
                if (imgLocatorForImageBytes.toString().length() != 0) {
                    String strScreenshotWithDelimiter = String.join(",", dataMapForImageBytes);
                    dataMapForTestStepStatus.put("Screenshot", imgByte);
                }
            } catch (Exception e) {
            }
        }
    }

    ;

    private void beforeScenario(TestCaseStarted event) {
        dataMapForTestStepStatus.clear();
    }

    ;
    static String strUserDirectory = System.getProperty("user.dir")+"\\reports\\images\\";


    private void afterScenarioComplete(TestCaseFinished event) {
        String strScenarioOutline = event.getTestCase().getName();
        String strFinalImageLocation = "";

        if (event.getResult().getStatus().isOk() == false) {
            System.out.println("Scenario Outline: " + strScenarioOutline + "\n");
            String strExecutionStepsAndStatus = getScenarioExecutionDetails(dataMapForTestStepStatus);
            System.out.println("\n >>>> Execution Steps & Status : \n" + strExecutionStepsAndStatus);

            if (!(dataMapForTestStepStatus.get("Screenshot") == null)) try {
                byte[] imgBytes = (byte[]) dataMapForTestStepStatus.get("Screenshot");
                strFinalImageLocation = strUserDirectory + "imgScreenshot.png";
                FileUtils.writeByteArrayToFile(new File(strFinalImageLocation), imgBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("\n >> Image Location : " + strFinalImageLocation);

            cardCreation(strScenarioOutline, strExecutionStepsAndStatus, "62e76a620681eb2e34f93fd1", "63d0a2337487ca7eac22745a" ,strFinalImageLocation );


        }

//        Updating the execution status in kualitee
        System.out.println("----> Before update: ");
        System.out.println("-- strScenarioOutline : Listener: "+strScenarioOutline);
        System.out.println("-- strScenarioOutline : Listener: "+event.getResult().getStatus().isOk());
        updateExecutionStatus(getTestCaseDetails(strScenarioOutline),event.getResult().getStatus().isOk());
//        updateExecutionStatusLatest(getTestCaseDetails(strScenarioOutline),event.getResult().getStatus().isOk());
    }

    ;

    public static String getScenarioExecutionDetails(LinkedHashMap<Object, Object> dataMap) {
        StringBuilder strDescription = new StringBuilder();
        for (Map.Entry<Object, Object> entry : dataMap.entrySet()) {
            try {
                if (!(entry.getKey().toString().equalsIgnoreCase("Exception") || entry.getKey().toString().equalsIgnoreCase("Screenshot"))) {
                    strDescription = strDescription.append(entry.getKey() + " :: " + entry.getValue() + "\n");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("\n Warning: Unable to retrieve Execution details in Concurrent Event Listener...");
            }
        }
        try {
            String strException = (String) dataMap.get("Exception");
            if (!(strException.isEmpty())) strDescription = strDescription.append("Exception: " + strException);
        } catch (Exception ex) {
        }
        return strDescription.toString();
    }

}
