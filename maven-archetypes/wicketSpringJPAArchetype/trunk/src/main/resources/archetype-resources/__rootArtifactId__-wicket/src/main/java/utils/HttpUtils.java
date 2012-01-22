package ${package}.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.wicket.request.cycle.RequestCycle;

/**
 * This utility class provides access to the HTTP information that triggered the current Wicket request.
 * Wicket normally hides this information from us, but occasionally we need access to things like the raw URL or HTTP headers.
 * 
 * @author Kamalpreet Singh
 *
 */
public class HttpUtils {

    /**
     * Returns the {@link HttpServletRequest} associated with the current request, 
     * or {@code null} if not found.
     * 
     * @return returns the {@link HttpServletRequest} associated with the current request, or {@code null} if not found.
     */
    public static HttpServletRequest getHttpServletRequest() {
        Object request = RequestCycle.get().getRequest().getContainerRequest();
        
        if (request instanceof HttpServletRequest) {
            return (HttpServletRequest) request;
        }
        
        return null;
    }
    
    /**
     * Returns the {@link HttpServletResponse} associated with the current request, 
     * or {@code null} if not found.
     * 
     * @return returns the {@link HttpServletResponse} associated with the current request, or {@code null} if not found.
     */
    public static HttpServletResponse getHttpServletResponse() {
        Object response = RequestCycle.get().getResponse().getContainerResponse();
        
        if (response instanceof HttpServletResponse) {
            return (HttpServletResponse) response;
        }
        
        return null;
    }
}
