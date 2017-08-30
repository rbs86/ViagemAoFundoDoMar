package exercicio.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exercicio.entity.Movement;
import exercicio.entity.Submarino;
import exercicio.exception.IllegalMovement;

public class ControladorTest {

	private Controlador controlador;
	private Submarino submarino;

	@Before
	public void setUp() {
		this.submarino = new Submarino();
		this.controlador = new Controlador(this.submarino);
	}

	@Test
	public void executeMovimentMoveTest() throws IllegalMovement {
		addMovements("M");
		controlador.executeMovements();
		String coordenadas = controlador.recuperarCoordenadasSubmarino();
		assertEquals("0 1 0 NORTE", coordenadas);
	}

	@Test
	public void executeMovimentLeftTest() throws IllegalMovement {
		addMovements("L");
		controlador.executeMovements();
		String coordenadas = controlador.recuperarCoordenadasSubmarino();
		assertEquals("0 0 0 OESTE", coordenadas);
	}

	@Test
	public void executeMovimentRightTest() throws IllegalMovement {
		addMovements("R");
		controlador.executeMovements();
		String coordenadas = controlador.recuperarCoordenadasSubmarino();
		assertEquals("0 0 0 LESTE", coordenadas);
	}

	@Test
	public void executeMovimentDownTest() throws IllegalMovement {
		addMovements("D");
		controlador.executeMovements();
		String coordenadas = controlador.recuperarCoordenadasSubmarino();
		assertEquals("0 0 -1 NORTE", coordenadas);
	}

	@Test
	public void executeMovimentUpTest() throws IllegalMovement {
		addMovements("D");
		addMovements("D");
		addMovements("U");
		controlador.executeMovements();
		String coordenadas = controlador.recuperarCoordenadasSubmarino();
		assertEquals("0 0 -1 NORTE", coordenadas);
	}

	@Test
	public void executeMultipleMovimentsTest() throws IllegalMovement {
		addMovements("LMRDDMMUU");
		controlador.executeMovements();
		String coordenadas = controlador.recuperarCoordenadasSubmarino();
		assertEquals("-1 2 0 NORTE", coordenadas);
	}

	private void addMovements(String movs) throws IllegalMovement {
		String[] movements = movs.split("");
		List<Movement> movementList = new ArrayList<>();
		for (String mov : movements) {
			movementList.add(Movement.parse(mov));
		}
		controlador.addMovements(movementList);
	}
}
