package management.speech;

import java.io.File;

import management.speech.recognizer.Recognizer;
import management.speech.request.Languages;
import management.speech.response.GoogleResponse;
import management.view.windows.SearchWindow;
import management.view.windows.SpeechTemplateFrame;
import management.speech.microphone.Microphone;
import net.sourceforge.javaflacencoder.FLACFileWriter;

public class SpeechInput extends Thread{

	private SpeechTemplateFrame frame;

	public SpeechInput(SpeechTemplateFrame frame) {
		
		this.frame = frame;
	}

	public void recording() {
		String result = "";
		Microphone microphone = new Microphone(FLACFileWriter.FLAC);
		File file = new File("temp/test.flac");
		try {
			microphone.captureAudioToFile(file);
		} catch (Exception ex) {
			System.out.println("ERROR: Microphone is not availible.");
			ex.printStackTrace();
		}
		
		frame.setRecording_label("Recording..."); // update the label on the addPerson frame (recording started)
		try {
			System.out.println("Recording...");
			if(frame instanceof SearchWindow) 
				Thread.sleep(2000); // add 2 more seconds for dictating the question
			Thread.sleep(3000);
			microphone.close();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		microphone.close();
		frame.setRecording_label(""); // update the label on the addPerson frame (recording stopped)
		System.out.println("Recording stopped.");
		
		/* to extend in the future if it needs to add more languages*/
		Languages language = Languages.ENGLISH_US;
		if(frame.italianSelected())
			language = Languages.ITALIAN;

		Recognizer recognizer = new Recognizer(language, System.getProperty("GOOGLE_API_KEY"));

		try {
			int maxNumOfResponses = 1;
			System.out.println("Sample rate is: " + (int) microphone.getAudioFormat().getSampleRate());
			GoogleResponse response = recognizer.getRecognizedDataForFlac(file, maxNumOfResponses, (int) microphone.getAudioFormat().getSampleRate());
			System.out.println("Google Response: " + response);
			result = response.results[0].alternatives[0].transcript;
			frame.setTextField(result);
		} catch (Exception ex) {
			System.out.println("ERROR: Google cannot be contacted");
			ex.printStackTrace();
		}

		file.deleteOnExit();
	}

}
