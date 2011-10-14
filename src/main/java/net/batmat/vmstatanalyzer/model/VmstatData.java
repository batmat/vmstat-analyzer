package net.batmat.vmstatanalyzer.model;

// TODO : improve data model, anemic seems stupid here. There has to be a better way.
public class VmstatData {
	String[] titles;
	String[][] values;

	public void setTitles(String[] strings) {
		// TODO : check that titles are unique
		titles = strings;
	}

	public void setValues(String[][] values) {
		this.values = values;
	}

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		for (String title : titles) {
			buffer.append(title).append(" | ");
		}
		buffer.append("\n");
		for (String[] vals : values) {
			for (String v : vals) {
				buffer.append(v).append(" | ");
			}
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public String[] getValuesFor(String title) {
		int index = findColumnIndex(title);
		String[] result = new String[values.length];
		int i=0;
		for(String[] v:values)
		{
			result[i++] = v[index];
		}
		return result;
	}

	private int findColumnIndex(String title) {
		int index = 0;
		for (String t : titles) {
			if (title.equals(t)) {
				return index;
			}
			index++;
		}
		throw new IllegalArgumentException(""+title+" column was not found.");
	}
}
