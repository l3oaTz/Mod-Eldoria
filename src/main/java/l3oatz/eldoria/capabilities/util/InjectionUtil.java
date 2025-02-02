package l3oatz.eldoria.capabilities.util;


import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Utility methods for automatic injection systems like {@link GameRegistry.ObjectHolder} and {@link CapabilityInject}.
 *
 * @author Choonster
 */
public class InjectionUtil {
    /**
     * Returns <code>null</code>.
     * <p>
     * This is used in the initialisers of <code>static final</code> fields instead of using <code>null</code> directly
     * to suppress the "Argument might be null" warnings from IntelliJ IDEA's "Constant conditions &amp; exceptions" inspection.
     * <p>
     * Based on diesieben07's solution <a href="http://www.minecraftforge.net/forum/topic/60980-solved-disable-%E2%80%9Cconstant-conditions-exceptions%E2%80%9D-inspection-for-field-in-intellij-idea/?do=findCommentcomment=285024">here</a>.
     *
     * @param <T> The field's type.
     * @return null
     */
	@SuppressWarnings({})
    public static <T> T Null() {
        return null;
    }
}