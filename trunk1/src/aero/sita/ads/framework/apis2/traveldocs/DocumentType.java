package aero.sita.ads.framework.apis2.traveldocs;

/**
 * The enum class for document types
 * @author jsingh
 *
 */
public class DocumentType {
	
	private int docType;
	
	public static final DocumentType VISA = new DocumentType(1);
	public static final DocumentType PASSPORT = new DocumentType(2);
	public static final DocumentType ALIEN_REG = new DocumentType(3);
	
	private DocumentType(int type){
		this.docType = type;
	}
}

