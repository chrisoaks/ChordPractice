package oaks;

public class ThreadExtend extends Thread {
	public void run() {
		int i = 1;
		for (int n =0;n<100;n++) {
			try {

				ChordPracticeFrame.frame.lblCMajor.setText("");

				String S = ChordPracticeFrame.frame.gen();
				//System.out.println("S = " + S);
				ChordPracticeFrame.frame.lblCMajor.setText(S);
				ChordPracticeFrame.frame.lblCMajor.paintImmediately(ChordPracticeFrame.frame.lblCMajor.getVisibleRect());
				//ChordPracticeFrame.frame.contentPane.add(ChordPracticeFrame.frame.lblCMajor);
				i++;
				Thread.sleep(ChordPracticeFrame.frame.delaybetweenchords);
			}
			catch(InterruptedException E) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
}
