package matrix;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class MatrixTest {

	@Test
	void test() {
		double[] myElements = {10, 20, 30, 40, 50, 60};
		Matrix matrix = new Matrix(2, 3, myElements);
		assertEquals(2, matrix.getRowCount());
		assertEquals(3, matrix.getColumnCount());
		assertArrayEquals(new double[] {10, 20, 30, 40, 50, 60}, matrix.getElementsRowMajor());
		assertArrayEquals(new double[] {10, 40, 20, 50, 30, 60}, matrix.getElementsColumnMajor());
		assertTrue(Arrays.deepEquals(new double[][] {
			{10, 20, 30},
			{40, 50, 60}
		}, matrix.getRows()));
		assertEquals(60, matrix.getElementAt(1, 2));
		
		assertArrayEquals(new double[] {100, 200, 300, 400, 500, 600},
				matrix.scaled(10).getElementsRowMajor());
		
		Matrix andereMatrix = new Matrix(2, 3, new double[] {1, 2, 3, 4, 5, 6});
		assertArrayEquals(new double[] {11, 22, 33, 44, 55, 66},
				matrix.plus(andereMatrix).getElementsRowMajor());
		
		double[] elems = matrix.getElementsRowMajor();
		elems[0] = 0;
		assertEquals(10, matrix.getElementAt(0, 0)); // Checks for representation exposure
		
		elems = matrix.getElementsColumnMajor();
		elems[0] = 0;
		assertEquals(10, matrix.getElementAt(0, 0)); // Checks for representation exposure
	}

}
