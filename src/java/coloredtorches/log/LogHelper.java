package coloredtorches.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Helper class witch provides logging system.
 * 
 * @author Julien Rosset
 */
public class LogHelper
{
    /**
     * Instance of basic logging system.
     */
    private static Logger logger = LogManager.getLogger("ColoredTorches");
    /**
     * Array of opened blocks.
     * @see startBlock.
     */
    private static List<LogBlock> blocks = new ArrayList<LogBlock>();
	private static int root_indent;
        
    /**
     * Log a message.
     * 
     * @param level     Log level.
     * @param message   Message to log.
     */
    public static void log (Level level, String message)
    {
    	message = String.join("", Collections.nCopies(root_indent, LogBlock.INDENT_STR)) + message;
    	
        for(LogBlock block : blocks)
            message = block + message;
        
        logger.log(level, message);
    }

    /**
     * Log a TRACE message.
     * 
     * @param message Message to log.
     */
    public static void trace (String message)
    {
        LogHelper.log(Level.TRACE, message);
    }
    /**
     * Log a DEBUG message.
     * 
     * @param message Message to log.
     */
    public static void debug (String message)
    {
        LogHelper.log(Level.DEBUG, message);
    }
    /**
     * Log a INFO message.
     * 
     * @param message Message to log.
     */
    public static void info (String message)
    {
        LogHelper.log(Level.INFO, message);
    }
    /**
     * Log a WARN message.
     * 
     * @param message Message to log.
     */
    public static void warn (String message)
    {
        LogHelper.log(Level.WARN, message);
    }
    /**
     * Log a ERROR message.
     * 
     * @param message Message to log.
     */
    public static void error (String message)
    {
        LogHelper.log(Level.ERROR, message);
    }
    /**
     * Log a FATAL message.
     * 
     * @param message Message to log.
     */
    public static void fatal (String message)
    {
        LogHelper.log(Level.FATAL, message);
    }

    /**
     * Start a block in logger : a repeat name before each message in the block.
     * Blocks can be nested.
     * 
     * @param name The displayed name.
     */
    public static void startBlock (String name)
    {
        blocks.add(new LogBlock(name));
    }
    /**
     * End a block.
     */
    public static void stopBlock ()
    {
        blocks.remove(blocks.size() - 1);
    }

	/**
	 * Start a new indent in the current (last) block.
	 */
	public static void startIndent()
	{
		if(blocks.size() == 0)
			root_indent++;
		else
			blocks.get(blocks.size() - 1).startIndent();
	}
	/**
	 * Stop the current (last) indent in the current (last) block.
	 */
	public static void stopIndent()
	{
		if(blocks.size() == 0)
			root_indent--;
		else
			blocks.get(blocks.size() - 1).stopIndent();
	}

	public static String side()
	{
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side.isServer())
			return "server";
		else if(side.isClient())
			return "client";
		else
			return "unknown";
	}
}
