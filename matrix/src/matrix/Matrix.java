package matrix;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Each instance of this class represents a matrix with a given number of rows and columns
 * and double-valued elements.
 * 
 * @invar | 0 <= getRowCount()
 * @invar | 0 <= getColumnCount()
 * @invar | getRows() != null
 * @invar | getRows().length == getRowCount()
 * @invar | Arrays.stream(getRows()).allMatch(row -> row != null && row.length == getColumnCount())
 * 
 * @immutable
 */
public class Matrix {
	
	/**
	 * @invar | 0 <= rowCount
	 * @invar | 0 <= columnCount
	 * @invar | elementsColumnMajor != null
	 * @invar | elementsColumnMajor.length == rowCount * columnCount
	 */
	private int rowCount;
	private int columnCount;
	/**
	 * @representationObject
	 */
	private double[] elementsColumnMajor;
	
	public int getRowCount() { return rowCount; }
	
	public int getColumnCount() { return columnCount; }
	
	/**
	 * @creates | result, ...result
	 */
	public double[][] getRows() {
		double[][] result = new double[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				result[i][j] = elementsColumnMajor[j * rowCount + i];
		return result;
	}
	
	/**
	 * @pre | 0 <= rowIndex && rowIndex < getRowCount()
	 * @pre | 0 <= columnIndex && columnIndex < getColumnCount()
	 * @post | result == getRows()[rowIndex][columnIndex]
	 */
	public double getElementAt(int rowIndex, int columnIndex) {
		return elementsColumnMajor[columnIndex * rowCount + rowIndex];
	}
	
	/**
	 * @post | result != null
	 * @post | result.length == getRowCount() * getColumnCount()
	 * @post | IntStream.range(0, getRowCount()).allMatch(i ->
	 *       |     IntStream.range(0, getColumnCount()).allMatch(j ->
	 *       |         result[i * getColumnCount() + j] == getRows()[i][j]
	 *       |     )
	 *       | )
	 * @creates | result
	 */
	public double[] getElementsRowMajor() {
		double[] result = new double[rowCount * columnCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				result[i * columnCount + j] = elementsColumnMajor[j * rowCount + i];
		return result;
	}
	
	/**
	 * @post | result != null
	 * @post | result.length == getRowCount() * getColumnCount()
	 * @post | IntStream.range(0, getRowCount()).allMatch(i ->
	 *       |     IntStream.range(0, getColumnCount()).allMatch(j ->
	 *       |         result[j * getRowCount() + i] == getRows()[i][j]
	 *       |     )
	 *       | )
	 * @creates | result
	 */
	public double[] getElementsColumnMajor() {
		return elementsColumnMajor.clone(); // Vermijd representation exposure!!!
	}
	
	/**
	 * @inspects | this
	 * @post | result != null
	 * @post | result.getRowCount() == getRowCount()
	 * @post | result.getColumnCount() == getColumnCount()
	 * @post | IntStream.range(0, getRowCount()).allMatch(i ->
	 *       |     IntStream.range(0, getColumnCount()).allMatch(j ->
	 *       |         result.getElementAt(i, j) == getElementAt(i, j) * factor
	 *       |     )
	 *       | )
	 * @creates | result
	 */
	public Matrix scaled(double factor) {
		double[] newElementsRowMajor = new double[rowCount * columnCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				newElementsRowMajor[i * columnCount + j] = elementsColumnMajor[j * rowCount + i] * factor;
		return new Matrix(rowCount, columnCount, newElementsRowMajor);
	}

	/**
	 * @pre | other != null
	 * @pre | other.getRowCount() == getRowCount()
	 * @pre | other.getColumnCount() == getColumnCount()
	 * @inspects | this, other
	 * @post | result != null
	 * @post | result.getRowCount() == getRowCount()
	 * @post | result.getColumnCount() == getColumnCount()
	 * @post | IntStream.range(0, getRowCount()).allMatch(i ->
	 *       |     IntStream.range(0, getColumnCount()).allMatch(j ->
	 *       |         result.getElementAt(i, j) == getElementAt(i, j) + other.getElementAt(i, j)
	 *       |     )
	 *       | )
	 * @creates | result
	 */
	public Matrix plus(Matrix other) {
		double[] otherElementsRowMajor = other.getElementsRowMajor();
		double[] newElementsRowMajor = new double[rowCount * columnCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				newElementsRowMajor[i * columnCount + j] =
					elementsColumnMajor[j * rowCount + i] + otherElementsRowMajor[i * columnCount + j];
		return new Matrix(rowCount, columnCount, newElementsRowMajor);
	}
	
	/**
	 * @throws IllegalArgumentException | rowCount < 0
	 * @throws IllegalArgumentException | columnCount < 0
	 * @throws IllegalArgumentException | elementsRowMajor == null
	 * @throws IllegalArgumentException | elementsRowMajor.length != rowCount * columnCount
	 * @inspects | elementsRowMajor
	 * @post | getRowCount() == rowCount
	 * @post | getColumnCount() == columnCount
	 * @post | Arrays.equals(getElementsRowMajor(), elementsRowMajor)
	 */
	public Matrix(int rowCount, int columnCount, double[] elementsRowMajor) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		elementsColumnMajor = new double[rowCount * columnCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				elementsColumnMajor[j * rowCount + i] = elementsRowMajor[i * columnCount + j];
	}
}
