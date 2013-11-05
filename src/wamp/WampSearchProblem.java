package wamp;

import java.util.ArrayList;

import util.Grid;
import util.Grid.GridType;
import util.Operator;
import util.Part;
import util.SearchProblem;
import util.State;

public class WampSearchProblem extends SearchProblem {

	public WampSearchProblem(Operator[] operators, ArrayList<State> stateSpace,
			State initialState) {
		super(operators, stateSpace, initialState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean goalTest(State state) {
		// System.out.println(((WampState) state).getNumberOfConnectedParts());
		ArrayList<Part> Bulky = new ArrayList<Part>();

		boolean goal = (((WampState) state)
				.getGrid()
				.GetBulksRec(((WampState) state).getGrid().getParts().get(0),
						Bulky).size() == ((WampState) state).getGrid()
				.getParts().size());
		// System.out.println("###################################");
		// System.out.println(Bulky);
		// System.out.println(((WampState) state).getGrid().getParts());
		// System.out.println("###################################");
		return goal;
	}

	@Override
	public double pathCost(Object... actions) {
		return actions.length;
	}

	// This is the function that generate the next state from the current state

	@Override
	public State transferFunction(State input, Operator operator) {
		WampState currentState = (WampState) input;
		WampOperator currentOperator = (WampOperator) operator;
		Grid currentGrid = currentState.getGrid();
		// System.out.println(currentState);
		int partX = currentGrid.getParts().get(currentOperator.getPartIndex())
				.getLocation().x;
		int partY = currentGrid.getParts().get(currentOperator.getPartIndex())
				.getLocation().y;

		switch (currentOperator.getPartDirection()) {
		case UP: {
			if (partX == 0) {
				return null;
			}
			double i = currentState.getCost();
			for (partX = currentGrid.getParts()
					.get(currentOperator.getPartIndex()).getLocation().x; partX > 1; partX--) {

				if (currentGrid.getGridCells()[partX - 1][partY] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							i + 1);
				} else {
					if (currentGrid.getGridCells()[partX - 1][partY] == GridType.RobotPart) {
						// Apply changes on stop
						// System.out.println(currentOperator.getPartIndex()
						// + "UP");
						if (currentGrid.getParts()
								.get(currentOperator.getPartIndex()).getUp() == null) {
							WampState state = new WampState(
									currentState.getGrid().fix(currentOperator,
											partX, partY),
									currentState.getNumberOfConnectedParts() + 1,
									i + 1);
							System.out.println(state.getGrid());
							return state;
						} else {
							WampState state = new WampState(currentState
									.getGrid().fix(currentOperator, partX,
											partY),
									currentState.getNumberOfConnectedParts(),
									i + 1);
							System.out.println(state.getGrid());
							return state;
						}

					} else {
						// will be changed
						i += 1;

					}
				}
			}
			return null;

		}
		case DOWN: {

			if (partX == currentGrid.getLength() - 1) {
				return null;
			}
			double i = currentState.getCost();
			for (partX = currentGrid.getParts()
					.get(currentOperator.getPartIndex()).getLocation().x; partX < currentGrid
					.getLength() - 1; partX++) {

				if (currentGrid.getGridCells()[partX + 1][partY] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							i + 1);
				} else {
					if (currentGrid.getGridCells()[partX + 1][partY] == GridType.RobotPart) {
						// Apply changes on stop
						// System.out.println(currentOperator.getPartIndex()
						// + "DOWN");
						if (currentGrid.getParts()
								.get(currentOperator.getPartIndex()).getDown() == null) {
							WampState state = new WampState(
									currentState.getGrid().fix(currentOperator,
											partX, partY),
									currentState.getNumberOfConnectedParts() + 1,
									i + 1);
							System.out.println(state.getGrid());
							return state;
						} else {
							WampState state = new WampState(currentState
									.getGrid().fix(currentOperator, partX,
											partY),
									currentState.getNumberOfConnectedParts(),
									i + 1);
							System.out.println(state.getGrid());
							return state;
						}
					} else {
						// will be changed
						i += 1;

					}
				}
			}
			return null;

		}
		case LEFT: {
			if (partY == 0) {
				return null;
			}
			double i = currentState.getCost();
			for (partY = currentGrid.getParts()
					.get(currentOperator.getPartIndex()).getLocation().y; partY > 1; partY--) {

				if (currentGrid.getGridCells()[partX][partY - 1] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							i + 1);
				} else {
					if (currentGrid.getGridCells()[partX][partY - 1] == GridType.RobotPart) {
						// Apply changes on stop
						// System.out.println(currentOperator.getPartIndex()
						// + "LEFT");
						if (currentGrid.getParts()
								.get(currentOperator.getPartIndex()).getLeft() == null) {
							WampState state = new WampState(
									currentState.getGrid().fix(currentOperator,
											partX, partY),
									currentState.getNumberOfConnectedParts() + 1,
									i + 1);
							System.out.println(state.getGrid());
							return state;
						} else {
							WampState state = new WampState(currentState
									.getGrid().fix(currentOperator, partX,
											partY),
									currentState.getNumberOfConnectedParts(),
									i + 1);
							System.out.println(state.getGrid());
							return state;
						}
					} else {
						// will be changed
						i += 1;

					}
				}
			}
			return null;
		}
		case RIGHT: {

			if (partY == currentGrid.getWidth() - 1) {

				return null;
			}

			double i = currentState.getCost();
			for (partY = currentGrid.getParts()
					.get(currentOperator.getPartIndex()).getLocation().y; partY < currentGrid
					.getWidth() - 1; partY++) {

				if (currentGrid.getGridCells()[partX][partY + 1] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							i + 1);

				} else {
					if (currentGrid.getGridCells()[partX][partY + 1] == GridType.RobotPart) {
						// Apply changes on stop
						// System.out.println(currentOperator.getPartIndex()
						// + "RIGHT");
						if (currentGrid.getParts()
								.get(currentOperator.getPartIndex()).getRight() == null) {
							WampState state = new WampState(
									currentState.getGrid().fix(currentOperator,
											partX, partY),
									currentState.getNumberOfConnectedParts() + 1,
									i + 1);
							System.out.println(state.getGrid());
							return state;
						} else {
							WampState state = new WampState(currentState
									.getGrid().fix(currentOperator, partX,
											partY),
									currentState.getNumberOfConnectedParts(),
									i + 1);
							System.out.println(state.getGrid());
							return state;
						}
					} else {
						// will be changed
						i += 1;

					}
				}
			}
			return null;
		}

		}

		return null;
	}

	public State transferFunction2(State input, Operator operator) {
		WampState currentState = (WampState) input;
		WampOperator currentOperator = (WampOperator) operator;
		Grid currentGrid = currentState.getGrid();
		ArrayList<Part> AdjacentParts = new ArrayList<Part>();
		currentGrid.GetBulksRec(
				currentGrid.getParts().get(currentOperator.getPartIndex()),
				AdjacentParts);
		// System.out.println(AdjacentParts.toString());
		// System.out.println(currentState);
		// System.out.println(">>>>>>" +
		// currentGrid.getParts().get(0).getDown());
	
		switch (currentOperator.getPartDirection()) {

		case UP: {
			for (int i = 0; i < currentGrid.getLength(); i++) {
				for (Part p : AdjacentParts) {
					if (p.getLocation().x - i - 1 >= 0) {
						if (currentGrid.getGridCells()[p.getLocation().x - i
								- 1][p.getLocation().y] == GridType.Obstacle) {
							// Apply changes on stop
							if (p.getUp() == null) {
								// System.out.println("************************");
								// System.out.println(currentOperator
								// .getPartIndex() + "UP");
								WampState state = new WampState(currentState
										.getGrid().fix2(AdjacentParts,
												currentOperator, -i, 0), 0,
										currentState.getCost()
												+ AdjacentParts.size()
												* (i==0?1:i));
								System.out.println(state.getGrid());
								System.out.println(state.getCost());
								// System.out.println(state);
								// System.out.println("************************");
								return state;
							}
						} else {
							if (currentGrid.getGridCells()[p.getLocation().x
									- i - 1][p.getLocation().y] == GridType.RobotPart) {
								// Apply changes on stop
								if (p.getUp() == null) {
									// System.out
									// .println("ROBOT************************");
									// System.out.println(currentOperator
									// .getPartIndex() + "UP");
									WampState state = new WampState(
											currentState.getGrid().fix2(
													AdjacentParts,
													currentOperator, -i, 0), 0,
											currentState.getCost()
													+ AdjacentParts.size()
													* i);
									System.out.println(state.getGrid());
									System.out.println(state.getCost());
									// System.out.println(state);
									// System.out
									// .println("************************");
									return state;
								}
							} else {
								// update cost
								
							}
						}
					} else {
						return null;
					}
				}
			}
		}
		case DOWN: {
			for (int i = 0; i < currentGrid.getLength(); i++) {
				for (Part p : AdjacentParts) {
					if (p.getLocation().x + i + 1 < currentGrid.getLength()) {
						if (currentGrid.getGridCells()[p.getLocation().x + i
								+ 1][p.getLocation().y] == GridType.Obstacle) {
							// Apply changes on stop
							if (p.getDown() == null) {
								// System.out.println("************************");
								// System.out.println(currentOperator
								// .getPartIndex() + "DOWN");
								WampState state = new WampState(currentState
										.getGrid().fix2(AdjacentParts,
												currentOperator, i, 0), 0,
										currentState.getCost()
												+ AdjacentParts.size()
												* (i==0?1:i));
								System.out.println(state.getGrid());
								System.out.println(state.getCost());
								// System.out.println(state);
								// System.out.println("************************");
								return state;
							}
						} else {
							if (currentGrid.getGridCells()[p.getLocation().x
									+ i + 1][p.getLocation().y] == GridType.RobotPart) {
								// Apply changes on stop
								if (p.getDown() == null) {
									// System.out
									// .println("ROBOT************************");
									// System.out.println(currentOperator
									// .getPartIndex() + "DOWN");
									WampState state = new WampState(
											currentState.getGrid().fix2(
													AdjacentParts,
													currentOperator, i, 0), 0,
											currentState.getCost()
													+ AdjacentParts.size()
													* i);
									System.out.println(state.getGrid());
									System.out.println(state.getCost());
									// System.out.println(state);
									// System.out
									// .println("************************");
									return state;
								}
							} else {
								// update cost
								
							}
						}
					} else {
						return null;
					}
				}
			}
		}
		case LEFT: {

			for (int i = 0; i < currentGrid.getWidth(); i++) {
				for (Part p : AdjacentParts) {
					if (p.getLocation().y - i - 1 >= 0) {
						if (currentGrid.getGridCells()[p.getLocation().x][p
								.getLocation().y - i - 1] == GridType.Obstacle) {
							// Apply changes on stop
							if (p.getLeft() == null) {
								// System.out.println("************************");
								// System.out.println(currentOperator
								// .getPartIndex() + "LEFT");
								WampState state = new WampState(currentState
										.getGrid().fix2(AdjacentParts,
												currentOperator, 0, -i), 0,
										currentState.getCost()
												+ AdjacentParts.size()
												* (i==0?1:i));
								System.out.println(state.getGrid());
								System.out.println(state.getCost());
								// System.out.println(state);
								// System.out.println("************************");
								return state;
							}
						} else {
							if (currentGrid.getGridCells()[p.getLocation().x][p
									.getLocation().y - i - 1] == GridType.RobotPart) {
								// Apply changes on stop
								if (p.getLeft() == null) {
									// System.out
									// .println("************************");
									// System.out.println(currentOperator
									// .getPartIndex() + "LEFT");
									WampState state = new WampState(
											currentState.getGrid().fix2(
													AdjacentParts,
													currentOperator, 0, -i), 0,
											currentState.getCost()
													+ AdjacentParts.size()
													* i);
									System.out.println(state.getGrid());
									System.out.println(state.getCost());
									// System.out.println(state);
									// System.out
									// .println("************************");
									return state;
								}
							} else {
								// update cost
								
							}
						}
					} else {
						return null;
					}
				}
			}
		}
		case RIGHT: {
			for (int i = 0; i < currentGrid.getWidth(); i++) {
				for (Part p : AdjacentParts) {
					if (p.getLocation().y + i + 1 < currentGrid.getWidth()) {
						if (currentGrid.getGridCells()[p.getLocation().x][p
								.getLocation().y + i + 1] == GridType.Obstacle) {
							// Apply changes on stop
							if (p.getRight() == null) {
								// System.out.println("************************");
								// System.out.println(currentOperator
								// .getPartIndex() + "RIGHT");
								WampState state = new WampState(currentState
										.getGrid().fix2(AdjacentParts,
												currentOperator, 0, i), 0,
										currentState.getCost()
												+ AdjacentParts.size()
												* (i==0?1:i));
								System.out.println(state.getGrid());
								System.out.println(state.getCost());
								// System.out.println(state);
								// System.out.println("************************");
								return state;
							}
						} else {
							if (currentGrid.getGridCells()[p.getLocation().x][p
									.getLocation().y + i + 1] == GridType.RobotPart) {
								// Apply changes on stop
								if (p.getRight() == null) {
									// System.out
									// .println("************************");
									// System.out.println(currentOperator
									// .getPartIndex() + "RIGHT");
									WampState state = new WampState(
											currentState.getGrid().fix2(
													AdjacentParts,
													currentOperator, 0, i), 0,
											currentState.getCost()
													+ AdjacentParts.size()
													* i);
									System.out.println(state.getGrid());
									System.out.println(state.getCost());
									// System.out.println(state);
									// System.out
									// .println("************************");
									return state;
								}
							} else {
								// update cost
								
							}
						}
					} else {
						return null;
					}
				}
			}
		}

		}

		return null;
	}
}
