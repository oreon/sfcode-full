package org.xtext.example.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.xtext.example.services.EntitiesGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class InternalEntitiesParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'.'", "'package'", "'{'", "'}'", "'type'", "'property'", "':'", "'[]'", "'entity'", "'extends'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_STRING=6;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=5;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

        public InternalEntitiesParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g"; }


     
     	private EntitiesGrammarAccess grammarAccess;
     	
        public InternalEntitiesParser(TokenStream input, IAstFactory factory, EntitiesGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("org/xtext/example/parser/antlr/internal/InternalEntities.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Model";	
       	} 



    // $ANTLR start entryRuleModel
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:72:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:72:47: (iv_ruleModel= ruleModel EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:73:2: iv_ruleModel= ruleModel EOF
            {
             currentNode = createCompositeNode(grammarAccess.getModelRule(), currentNode); 
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel73);
            iv_ruleModel=ruleModel();
            _fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel83); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleModel


    // $ANTLR start ruleModel
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:80:1: ruleModel returns [EObject current=null] : (lv_elements_0= rulePackage )* ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:85:6: ( (lv_elements_0= rulePackage )* )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:86:1: (lv_elements_0= rulePackage )*
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:86:1: (lv_elements_0= rulePackage )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:89:6: lv_elements_0= rulePackage
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getModelAccess().getElementsPackageParserRuleCall_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePackage_in_ruleModel141);
            	    lv_elements_0=rulePackage();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getModelRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "elements", lv_elements_0, "Package", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleModel


    // $ANTLR start entryRuleJAVAID
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:116:1: entryRuleJAVAID returns [EObject current=null] : iv_ruleJAVAID= ruleJAVAID EOF ;
    public final EObject entryRuleJAVAID() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleJAVAID = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:116:48: (iv_ruleJAVAID= ruleJAVAID EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:117:2: iv_ruleJAVAID= ruleJAVAID EOF
            {
             currentNode = createCompositeNode(grammarAccess.getJAVAIDRule(), currentNode); 
            pushFollow(FOLLOW_ruleJAVAID_in_entryRuleJAVAID180);
            iv_ruleJAVAID=ruleJAVAID();
            _fsp--;

             current =iv_ruleJAVAID; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleJAVAID190); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleJAVAID


    // $ANTLR start ruleJAVAID
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:124:1: ruleJAVAID returns [EObject current=null] : ( (lv_name_0= RULE_ID ) ( '.' RULE_ID )* ) ;
    public final EObject ruleJAVAID() throws RecognitionException {
        EObject current = null;

        Token lv_name_0=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:129:6: ( ( (lv_name_0= RULE_ID ) ( '.' RULE_ID )* ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:130:1: ( (lv_name_0= RULE_ID ) ( '.' RULE_ID )* )
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:130:1: ( (lv_name_0= RULE_ID ) ( '.' RULE_ID )* )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:130:2: (lv_name_0= RULE_ID ) ( '.' RULE_ID )*
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:130:2: (lv_name_0= RULE_ID )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:132:6: lv_name_0= RULE_ID
            {
            lv_name_0=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJAVAID237); 

            		createLeafNode(grammarAccess.getJAVAIDAccess().getNameIDTerminalRuleCall_0_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getJAVAIDRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_0, "ID", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:150:2: ( '.' RULE_ID )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==11) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:150:3: '.' RULE_ID
            	    {
            	    match(input,11,FOLLOW_11_in_ruleJAVAID255); 

            	            createLeafNode(grammarAccess.getJAVAIDAccess().getFullStopKeyword_1_0(), null); 
            	        
            	    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleJAVAID264); 
            	     
            	        createLeafNode(grammarAccess.getJAVAIDAccess().getIDTerminalRuleCall_1_1(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleJAVAID


    // $ANTLR start entryRulePackage
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:165:1: entryRulePackage returns [EObject current=null] : iv_rulePackage= rulePackage EOF ;
    public final EObject entryRulePackage() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackage = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:165:49: (iv_rulePackage= rulePackage EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:166:2: iv_rulePackage= rulePackage EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPackageRule(), currentNode); 
            pushFollow(FOLLOW_rulePackage_in_entryRulePackage298);
            iv_rulePackage=rulePackage();
            _fsp--;

             current =iv_rulePackage; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePackage308); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePackage


    // $ANTLR start rulePackage
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:173:1: rulePackage returns [EObject current=null] : ( 'package' (lv_name_1= RULE_ID ) '{' (lv_properties_3= rulePackagedType )* '}' ) ;
    public final EObject rulePackage() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        EObject lv_properties_3 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:178:6: ( ( 'package' (lv_name_1= RULE_ID ) '{' (lv_properties_3= rulePackagedType )* '}' ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:179:1: ( 'package' (lv_name_1= RULE_ID ) '{' (lv_properties_3= rulePackagedType )* '}' )
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:179:1: ( 'package' (lv_name_1= RULE_ID ) '{' (lv_properties_3= rulePackagedType )* '}' )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:179:2: 'package' (lv_name_1= RULE_ID ) '{' (lv_properties_3= rulePackagedType )* '}'
            {
            match(input,12,FOLLOW_12_in_rulePackage342); 

                    createLeafNode(grammarAccess.getPackageAccess().getPackageKeyword_0(), null); 
                
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:183:1: (lv_name_1= RULE_ID )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:185:6: lv_name_1= RULE_ID
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rulePackage364); 

            		createLeafNode(grammarAccess.getPackageAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPackageRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "ID", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,13,FOLLOW_13_in_rulePackage381); 

                    createLeafNode(grammarAccess.getPackageAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:207:1: (lv_properties_3= rulePackagedType )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==12||LA3_0==15||LA3_0==19) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:210:6: lv_properties_3= rulePackagedType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getPackageAccess().getPropertiesPackagedTypeParserRuleCall_3_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_rulePackagedType_in_rulePackage415);
            	    lv_properties_3=rulePackagedType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getPackageRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "properties", lv_properties_3, "PackagedType", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            match(input,14,FOLLOW_14_in_rulePackage429); 

                    createLeafNode(grammarAccess.getPackageAccess().getRightCurlyBracketKeyword_4(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePackage


    // $ANTLR start entryRulePackagedType
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:239:1: entryRulePackagedType returns [EObject current=null] : iv_rulePackagedType= rulePackagedType EOF ;
    public final EObject entryRulePackagedType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePackagedType = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:239:54: (iv_rulePackagedType= rulePackagedType EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:240:2: iv_rulePackagedType= rulePackagedType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPackagedTypeRule(), currentNode); 
            pushFollow(FOLLOW_rulePackagedType_in_entryRulePackagedType462);
            iv_rulePackagedType=rulePackagedType();
            _fsp--;

             current =iv_rulePackagedType; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePackagedType472); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRulePackagedType


    // $ANTLR start rulePackagedType
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:247:1: rulePackagedType returns [EObject current=null] : (this_Type_0= ruleType | this_Package_1= rulePackage ) ;
    public final EObject rulePackagedType() throws RecognitionException {
        EObject current = null;

        EObject this_Type_0 = null;

        EObject this_Package_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:252:6: ( (this_Type_0= ruleType | this_Package_1= rulePackage ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:253:1: (this_Type_0= ruleType | this_Package_1= rulePackage )
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:253:1: (this_Type_0= ruleType | this_Package_1= rulePackage )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15||LA4_0==19) ) {
                alt4=1;
            }
            else if ( (LA4_0==12) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("253:1: (this_Type_0= ruleType | this_Package_1= rulePackage )", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:254:5: this_Type_0= ruleType
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPackagedTypeAccess().getTypeParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleType_in_rulePackagedType519);
                    this_Type_0=ruleType();
                    _fsp--;

                     
                            current = this_Type_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:264:5: this_Package_1= rulePackage
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getPackagedTypeAccess().getPackageParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_rulePackage_in_rulePackagedType546);
                    this_Package_1=rulePackage();
                    _fsp--;

                     
                            current = this_Package_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end rulePackagedType


    // $ANTLR start entryRuleType
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:279:1: entryRuleType returns [EObject current=null] : iv_ruleType= ruleType EOF ;
    public final EObject entryRuleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleType = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:279:46: (iv_ruleType= ruleType EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:280:2: iv_ruleType= ruleType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleType_in_entryRuleType578);
            iv_ruleType=ruleType();
            _fsp--;

             current =iv_ruleType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleType588); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleType


    // $ANTLR start ruleType
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:287:1: ruleType returns [EObject current=null] : (this_SimpleType_0= ruleSimpleType | this_Entity_1= ruleEntity ) ;
    public final EObject ruleType() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleType_0 = null;

        EObject this_Entity_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:292:6: ( (this_SimpleType_0= ruleSimpleType | this_Entity_1= ruleEntity ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:293:1: (this_SimpleType_0= ruleSimpleType | this_Entity_1= ruleEntity )
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:293:1: (this_SimpleType_0= ruleSimpleType | this_Entity_1= ruleEntity )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==15) ) {
                alt5=1;
            }
            else if ( (LA5_0==19) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("293:1: (this_SimpleType_0= ruleSimpleType | this_Entity_1= ruleEntity )", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:294:5: this_SimpleType_0= ruleSimpleType
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getSimpleTypeParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleSimpleType_in_ruleType635);
                    this_SimpleType_0=ruleSimpleType();
                    _fsp--;

                     
                            current = this_SimpleType_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:304:5: this_Entity_1= ruleEntity
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getTypeAccess().getEntityParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleEntity_in_ruleType662);
                    this_Entity_1=ruleEntity();
                    _fsp--;

                     
                            current = this_Entity_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleType


    // $ANTLR start entryRuleSimpleType
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:319:1: entryRuleSimpleType returns [EObject current=null] : iv_ruleSimpleType= ruleSimpleType EOF ;
    public final EObject entryRuleSimpleType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleType = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:319:52: (iv_ruleSimpleType= ruleSimpleType EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:320:2: iv_ruleSimpleType= ruleSimpleType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSimpleTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleSimpleType_in_entryRuleSimpleType694);
            iv_ruleSimpleType=ruleSimpleType();
            _fsp--;

             current =iv_ruleSimpleType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleType704); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSimpleType


    // $ANTLR start ruleSimpleType
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:327:1: ruleSimpleType returns [EObject current=null] : ( 'type' (lv_name_1= RULE_ID ) ) ;
    public final EObject ruleSimpleType() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:332:6: ( ( 'type' (lv_name_1= RULE_ID ) ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:333:1: ( 'type' (lv_name_1= RULE_ID ) )
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:333:1: ( 'type' (lv_name_1= RULE_ID ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:333:2: 'type' (lv_name_1= RULE_ID )
            {
            match(input,15,FOLLOW_15_in_ruleSimpleType738); 

                    createLeafNode(grammarAccess.getSimpleTypeAccess().getTypeKeyword_0(), null); 
                
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:337:1: (lv_name_1= RULE_ID )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:339:6: lv_name_1= RULE_ID
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSimpleType760); 

            		createLeafNode(grammarAccess.getSimpleTypeAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getSimpleTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "ID", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSimpleType


    // $ANTLR start entryRuleProperty
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:364:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:364:50: (iv_ruleProperty= ruleProperty EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:365:2: iv_ruleProperty= ruleProperty EOF
            {
             currentNode = createCompositeNode(grammarAccess.getPropertyRule(), currentNode); 
            pushFollow(FOLLOW_ruleProperty_in_entryRuleProperty801);
            iv_ruleProperty=ruleProperty();
            _fsp--;

             current =iv_ruleProperty; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleProperty811); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleProperty


    // $ANTLR start ruleProperty
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:372:1: ruleProperty returns [EObject current=null] : ( 'property' (lv_name_1= RULE_ID ) ':' ( RULE_ID ) (lv_many_4= '[]' )? ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        Token lv_many_4=null;

         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:377:6: ( ( 'property' (lv_name_1= RULE_ID ) ':' ( RULE_ID ) (lv_many_4= '[]' )? ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:378:1: ( 'property' (lv_name_1= RULE_ID ) ':' ( RULE_ID ) (lv_many_4= '[]' )? )
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:378:1: ( 'property' (lv_name_1= RULE_ID ) ':' ( RULE_ID ) (lv_many_4= '[]' )? )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:378:2: 'property' (lv_name_1= RULE_ID ) ':' ( RULE_ID ) (lv_many_4= '[]' )?
            {
            match(input,16,FOLLOW_16_in_ruleProperty845); 

                    createLeafNode(grammarAccess.getPropertyAccess().getPropertyKeyword_0(), null); 
                
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:382:1: (lv_name_1= RULE_ID )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:384:6: lv_name_1= RULE_ID
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleProperty867); 

            		createLeafNode(grammarAccess.getPropertyAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getPropertyRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "ID", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            match(input,17,FOLLOW_17_in_ruleProperty884); 

                    createLeafNode(grammarAccess.getPropertyAccess().getColonKeyword_2(), null); 
                
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:406:1: ( RULE_ID )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:409:3: RULE_ID
            {

            			if (current==null) {
            	            current = factory.create(grammarAccess.getPropertyRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
                    
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleProperty906); 

            		createLeafNode(grammarAccess.getPropertyAccess().getTypeTypeCrossReference_3_0(), "type"); 
            	

            }

            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:422:2: (lv_many_4= '[]' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==18) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:424:6: lv_many_4= '[]'
                    {
                    lv_many_4=(Token)input.LT(1);
                    match(input,18,FOLLOW_18_in_ruleProperty930); 

                            createLeafNode(grammarAccess.getPropertyAccess().getManyLeftSquareBracketRightSquareBracketKeyword_4_0(), "many"); 
                        

                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getPropertyRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                    	        
                    	        try {
                    	       		set(current, "many", true, "[]", lastConsumedNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	    

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleProperty


    // $ANTLR start entryRuleEntity
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:450:1: entryRuleEntity returns [EObject current=null] : iv_ruleEntity= ruleEntity EOF ;
    public final EObject entryRuleEntity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntity = null;


        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:450:48: (iv_ruleEntity= ruleEntity EOF )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:451:2: iv_ruleEntity= ruleEntity EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEntityRule(), currentNode); 
            pushFollow(FOLLOW_ruleEntity_in_entryRuleEntity977);
            iv_ruleEntity=ruleEntity();
            _fsp--;

             current =iv_ruleEntity; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEntity987); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEntity


    // $ANTLR start ruleEntity
    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:458:1: ruleEntity returns [EObject current=null] : ( 'entity' (lv_name_1= RULE_ID ) ( 'extends' ( RULE_ID ) )? '{' (lv_properties_5= ruleProperty )* ( RULE_ID )? '}' ) ;
    public final EObject ruleEntity() throws RecognitionException {
        EObject current = null;

        Token lv_name_1=null;
        EObject lv_properties_5 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:463:6: ( ( 'entity' (lv_name_1= RULE_ID ) ( 'extends' ( RULE_ID ) )? '{' (lv_properties_5= ruleProperty )* ( RULE_ID )? '}' ) )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:464:1: ( 'entity' (lv_name_1= RULE_ID ) ( 'extends' ( RULE_ID ) )? '{' (lv_properties_5= ruleProperty )* ( RULE_ID )? '}' )
            {
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:464:1: ( 'entity' (lv_name_1= RULE_ID ) ( 'extends' ( RULE_ID ) )? '{' (lv_properties_5= ruleProperty )* ( RULE_ID )? '}' )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:464:2: 'entity' (lv_name_1= RULE_ID ) ( 'extends' ( RULE_ID ) )? '{' (lv_properties_5= ruleProperty )* ( RULE_ID )? '}'
            {
            match(input,19,FOLLOW_19_in_ruleEntity1021); 

                    createLeafNode(grammarAccess.getEntityAccess().getEntityKeyword_0(), null); 
                
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:468:1: (lv_name_1= RULE_ID )
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:470:6: lv_name_1= RULE_ID
            {
            lv_name_1=(Token)input.LT(1);
            match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntity1043); 

            		createLeafNode(grammarAccess.getEntityAccess().getNameIDTerminalRuleCall_1_0(), "name"); 
            	

            	        if (current==null) {
            	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode, current);
            	        }
            	        
            	        try {
            	       		set(current, "name", lv_name_1, "ID", lastConsumedNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	    

            }

            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:488:2: ( 'extends' ( RULE_ID ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==20) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:488:3: 'extends' ( RULE_ID )
                    {
                    match(input,20,FOLLOW_20_in_ruleEntity1061); 

                            createLeafNode(grammarAccess.getEntityAccess().getExtendsKeyword_2_0(), null); 
                        
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:492:1: ( RULE_ID )
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:495:3: RULE_ID
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntity1083); 

                    		createLeafNode(grammarAccess.getEntityAccess().getExtendsEntityCrossReference_2_1_0(), "extends"); 
                    	

                    }


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleEntity1097); 

                    createLeafNode(grammarAccess.getEntityAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:512:1: (lv_properties_5= ruleProperty )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==16) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:515:6: lv_properties_5= ruleProperty
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getEntityAccess().getPropertiesPropertyParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleProperty_in_ruleEntity1131);
            	    lv_properties_5=ruleProperty();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        
            	    	        try {
            	    	       		add(current, "properties", lv_properties_5, "Property", currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:533:3: ( RULE_ID )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_ID) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g:536:3: RULE_ID
                    {

                    			if (current==null) {
                    	            current = factory.create(grammarAccess.getEntityRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode, current);
                    	        }
                            
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEntity1158); 

                    		createLeafNode(grammarAccess.getEntityAccess().getPackagePackageCrossReference_5_0(), "package"); 
                    	

                    }
                    break;

            }

            match(input,14,FOLLOW_14_in_ruleEntity1171); 

                    createLeafNode(grammarAccess.getEntityAccess().getRightCurlyBracketKeyword_6(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEntity


 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePackage_in_ruleModel141 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_ruleJAVAID_in_entryRuleJAVAID180 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleJAVAID190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJAVAID237 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_11_in_ruleJAVAID255 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleJAVAID264 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_rulePackage_in_entryRulePackage298 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePackage308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_rulePackage342 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_rulePackage364 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_rulePackage381 = new BitSet(new long[]{0x000000000008D000L});
    public static final BitSet FOLLOW_rulePackagedType_in_rulePackage415 = new BitSet(new long[]{0x000000000008D000L});
    public static final BitSet FOLLOW_14_in_rulePackage429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePackagedType_in_entryRulePackagedType462 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePackagedType472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_rulePackagedType519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePackage_in_rulePackagedType546 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleType_in_entryRuleType578 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleType588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_ruleType635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntity_in_ruleType662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleType_in_entryRuleSimpleType694 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleType704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleSimpleType738 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSimpleType760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleProperty_in_entryRuleProperty801 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProperty811 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleProperty845 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleProperty867 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_ruleProperty884 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleProperty906 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_18_in_ruleProperty930 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEntity_in_entryRuleEntity977 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEntity987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleEntity1021 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntity1043 = new BitSet(new long[]{0x0000000000102000L});
    public static final BitSet FOLLOW_20_in_ruleEntity1061 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntity1083 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleEntity1097 = new BitSet(new long[]{0x0000000000014010L});
    public static final BitSet FOLLOW_ruleProperty_in_ruleEntity1131 = new BitSet(new long[]{0x0000000000014010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEntity1158 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleEntity1171 = new BitSet(new long[]{0x0000000000000002L});

}