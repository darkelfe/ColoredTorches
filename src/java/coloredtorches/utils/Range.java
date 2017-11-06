package coloredtorches.utils;

/**
 * A range of integer.
 * 
 * @author Julien Rosset
 */
public class Range
{
	public int lowerBound;
	public int upperBound;
	
	public Range(int bound)
	{
		this(bound, bound);
	}
	public Range(int lower, int upper)
	{
		this.lowerBound = lower;
		this.upperBound = upper;
	}
	
	public boolean isInRange(int value)
	{
		return (this.lowerBound <= value && value <= this.upperBound);
	}

	@Override
	public String toString()
	{
		return this.lowerBound + " ~ " + this.upperBound;
	}
}
