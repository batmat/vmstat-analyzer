package net.batmat.vmstatanalyzer.model;

import java.io.IOException;

public interface VmstatDataLoader {
	VmstatData getData() throws IOException;
}
