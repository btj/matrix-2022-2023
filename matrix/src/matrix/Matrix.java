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
	
	public int getRowCount() { throw new RuntimeException("Not yet implemented"); }
	
	public int getColumnCount() { throw new RuntimeException("Not yet implemented"); }
	
	/**
	 * @creates | result, ...result
	 */
	public double[][] getRows() { throw new RuntimeException("Not yet implemented"); }
	
	/**
	 * @pre | 0 <= rowIndex && rowIndex < getRowCount()
	 * @pre | 0 <= columnIndex && columnIndex < getColumnCount()
	 * @post | result == getRows()[rowIndex][columnIndex]
	 */
	public double getElementAt(int rowIndex, int columnIndex) { throw new RuntimeException("Not yet implemented"); }
	
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
	public double[] getElementsRowMajor() { throw new RuntimeException("Not yet implemented"); }
	
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
	public double[] getElementsColumnMajor() { throw new RuntimeException("Not yet implemented"); }
	
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
	public Matrix scaled(double factor) { throw new RuntimeException("Not yet implemented"); }

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
	public Matrix plus(Matrix other) { throw new RuntimeException("Not yet implemented"); }
	
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
		throw new RuntimeException("Not yet implemented");
	}
}
