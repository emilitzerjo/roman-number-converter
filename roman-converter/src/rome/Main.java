package rome;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	private static final List<Pair<Integer, String>> list = new ArrayList<>();
	private static final List<Pair<String, String>> replaceRules = new ArrayList<>();
	static {
		list.add(new Pair<Integer, String>(1000, "M"));
		list.add(new Pair<Integer, String>(500, "D"));
		list.add(new Pair<Integer, String>(100, "C"));
		list.add(new Pair<Integer, String>(50, "L"));
		list.add(new Pair<Integer, String>(10, "X"));
		list.add(new Pair<Integer, String>(5, "V"));
		list.add(new Pair<Integer, String>(1, "I"));

		replaceRules.add(new Pair<String, String>("IIII", "IV"));
		replaceRules.add(new Pair<String, String>("XXXX", "XL"));
		replaceRules.add(new Pair<String, String>("CCCC", "CD"));
		replaceRules.add(new Pair<String, String>("VIV", "IX"));
		replaceRules.add(new Pair<String, String>("LXL", "XC"));
		replaceRules.add(new Pair<String, String>("DCD", "CM"));
	}

	public static void main(String[] args) {

		int arab = 0;
		String showInputDialog = JOptionPane
				.showInputDialog("Arabic Number (<3000):");
		while (!showInputDialog.matches("[0-9]*")
				|| Integer.valueOf(showInputDialog) > 2999) {
			showInputDialog = JOptionPane
					.showInputDialog("Wrong Input! Try Again:");
		}
		arab = Integer.valueOf(showInputDialog);
		JOptionPane.showMessageDialog(null, arab + " → " + convertToRome(arab),
				"Converter", JOptionPane.INFORMATION_MESSAGE);
	}

	private static String convertToRome(int arab) {
		if (arab > 2999) {
			throw new IllegalArgumentException("Number to big (>2999)");
		}
		StringBuilder rome = new StringBuilder();
		for (Pair<Integer, String> rule : list) {
			if (arab % rule.getWert1() != arab) {
				for (int i = 0; i < arab / rule.getWert1(); i++) {
					rome.append(rule.getWert2());
				}
				arab %= rule.getWert1();
			}
		}
		String romeResult = rome.toString();
		for (Pair<String, String> rule : replaceRules) {
			romeResult = romeResult.replace(rule.getWert1(), rule.getWert2());
		}
		return romeResult;
	}

	private static class Pair<A, B> {
		private A wert1;
		private B wert2;

		private Pair(A wert1, B wert2) {
			this.wert1 = wert1;
			this.wert2 = wert2;
		}

		public A getWert1() {
			return wert1;
		}

		public B getWert2() {
			return wert2;
		}
	}

}
