package net.batmat.vmstatanalyzer.model;

/**
 * Implementors must support having lines beginning with # ignored.
 */
public interface VmstatDataLoader {
	VmstatData getData() throws VmstatDataFormatException;
}
