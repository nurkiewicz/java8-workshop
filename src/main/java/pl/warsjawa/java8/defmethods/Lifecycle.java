package pl.warsjawa.java8.defmethods;

/**
 * @author Tomasz Nurkiewicz
 * @since 07.10.13, 22:04
 */
public interface Lifecycle {

	default int start() {
		return 1;
	}

}
