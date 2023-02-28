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
 */
public class Matrix {
	
	/**
	 * @invar | 0 <= rowCount
	 * @invar | 0 <= columnCount
	 * @invar | elementsRowMajor != null
	 * @invar | elementsRowMajor.length == rowCount * columnCount
	 */
	private int rowCount;
	private int columnCount;
	/**
	 * @representationObject
	 */
	private double[] elementsRowMajor;
	
	public int getRowCount() { return rowCount; }
	
	public int getColumnCount() { return columnCount; }
	
	/**
	 * @creates | result, ...result
	 */
	public double[][] getRows() {
		double[][] result = new double[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				result[i][j] = elementsRowMajor[i * columnCount + j];
		return result;
	}
	
	/**
	 * @pre | 0 <= rowIndex && rowIndex < getRowCount()
	 * @pre | 0 <= columnIndex && columnIndex < getColumnCount()
	 * @post | result == getRows()[rowIndex][columnIndex]
	 */
	public double getElementAt(int rowIndex, int columnIndex) {
		return elementsRowMajor[rowIndex * columnCount + columnIndex];
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
		return elementsRowMajor.clone(); // Vermijd representation exposure!!
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
		double[] result = new double[rowCount * columnCount];
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				result[j * rowCount + i] = elementsRowMajor[i * columnCount + j];
		return result;
	}
	
	/**
	 * @mutates | this
	 * @post | getRowCount() == old(getRowCount())
	 * @post | getColumnCount() == old(getColumnCount())
	 * @post | IntStream.range(0, getRowCount()).allMatch(i ->
	 *       |     IntStream.range(0, getColumnCount()).allMatch(j ->
	 *       |         getElementAt(i, j) == old(getRows())[i][j] * factor
	 *       |     )
	 *       | )
	 */
	public void scale(double factor) {
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				elementsRowMajor[i * columnCount + j] = elementsRowMajor[i * columnCount + j] * factor;
	}

	/**
	 * @pre | other != null
	 * @pre | other.getRowCount() == getRowCount()
	 * @pre | other.getColumnCount() == getColumnCount()
	 * @mutates | this
	 * @inspects | other
	 * @post | getRowCount() == old(getRowCount())
	 * @post | getColumnCount() == old(getColumnCount())
	 * @post | IntStream.range(0, getRowCount()).allMatch(i ->
	 *       |     IntStream.range(0, getColumnCount()).allMatch(j ->
	 *       |         getElementAt(i, j) == old(getRows())[i][j] + other.getElementAt(i, j)
	 *       |     )
	 *       | )
	 */
	public void add(Matrix other) {
		double[] otherElementsRowMajor = other.getElementsRowMajor();
		for (int i = 0; i < rowCount; i++)
			for (int j = 0; j < columnCount; j++)
				elementsRowMajor[i * columnCount + j] =
					elementsRowMajor[i * columnCount + j] + otherElementsRowMajor[i * columnCount + j];
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
		this.elementsRowMajor = elementsRowMajor.clone(); // Vermijd representation exposure!
	}
}
