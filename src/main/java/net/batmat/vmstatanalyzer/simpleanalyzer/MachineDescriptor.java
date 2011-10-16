package net.batmat.vmstatanalyzer.simpleanalyzer;

public class MachineDescriptor {

	private final int cpuCount;

	public int getCpuCount() {
		return cpuCount;
	}

	public MachineDescriptor(int cpuCount) {
		this.cpuCount = cpuCount;
	}
}
