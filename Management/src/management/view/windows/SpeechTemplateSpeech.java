package management.view.windows;

import javax.swing.JFrame;

public abstract class SpeechTemplateSpeech extends JFrame {
	public SpeechTemplateSpeech() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void setRecording_label(String text);
	public abstract void setTextField(String text);

}
