package management.view.windows;

import javax.swing.JFrame;

public abstract class SpeechTemplateFrame extends JFrame {
	public SpeechTemplateFrame() {
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract boolean englishSelected();
	public abstract boolean italianSelected();
	public abstract void setRecording_label(String text);
	public abstract void setTextField(String text);
	

}
