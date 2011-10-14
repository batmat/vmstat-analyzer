package net.batmat.vmstatanalyzer.simpleanalyzer;

import net.batmat.vmstatanalyzer.model.VmstatAnalyzer;
import net.batmat.vmstatanalyzer.model.VmstatData;

public class SimpleAnalyzer implements VmstatAnalyzer {

	private final VmstatData data;
	private final StringBuilder builder;

	public SimpleAnalyzer(VmstatData data) {
		this.data = data;
		builder = new StringBuilder();
	}

	public String getReport() {
		findDominatingConsumer();
		return builder.toString();
	}

	/**
	 * Checks the proportion of lines dominated (>90%) by userspace or not
	 */
	private void findDominatingConsumer() {
		String[] usValues = data.getValuesFor("us");

		int userSpaceDominance = 0;
		for (String v : usValues) {
			if (Integer.parseInt(v) > 90) {
				userSpaceDominance++;
			}
		}
		float userSpaceProportion = (userSpaceDominance / (usValues.length * 1.0f));
		if (userSpaceDominance > 0.9f) {
			builder.append("User space is dominating");
		} else if (userSpaceDominance > 0.7f) {
			builder.append("User space is mostly dominating");
		} else if (userSpaceDominance > 0.5f) {
			builder.append("User space is not really dominating");
		}
		else{
			builder.append("System is dominating");
		}
		builder.append("(us="+userSpaceProportion*100+"%)");
	}

}
