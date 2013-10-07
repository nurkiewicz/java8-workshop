package pl.warsjawa.java8.defmethods;


public interface Job {

	/**
	 * Do not TOUCH!
	 */
	default int start() {
		return 2;
	}

}
