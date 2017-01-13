package de.lette.mensaplan.server;

public enum Action {
	LIKE("like"), DISLIKE("dislike"), RESET("reset");

	private String stringRepresentation;

	private Action(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

	public String getStringRepresentation() {
		return stringRepresentation;
	}

	@Override
	public String toString() {
		return getStringRepresentation();
	}

	public static Action fromString(String s) {
		for(Action a : Action.values()) {
			if(a.getStringRepresentation().equals(s)) {
				return a;
			}
		}
		return null;
	}
}