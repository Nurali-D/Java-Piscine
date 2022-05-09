package ex01;

public final class UserIdsGenerator {

	private static UserIdsGenerator instance;
	private static Integer idsCounter = 0;

	private UserIdsGenerator() {
	}

	public static UserIdsGenerator getInstance() {
		if (instance == null) {
			instance = new UserIdsGenerator();
		}
		return instance;
	}

	public int generateId() {
		++idsCounter;
		return idsCounter;
	}
}
