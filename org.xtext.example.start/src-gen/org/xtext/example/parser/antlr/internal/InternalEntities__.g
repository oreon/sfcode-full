lexer grammar InternalEntities;
@header {
package org.xtext.example.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : '.' ;
T12 : 'package' ;
T13 : '{' ;
T14 : '}' ;
T15 : 'type' ;
T16 : 'property' ;
T17 : ':' ;
T18 : '[]' ;
T19 : 'entity' ;
T20 : 'extends' ;

// $ANTLR src "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g" 559
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g" 561
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g" 563
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g" 565
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g" 567
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g" 569
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.xtext.example.start/src-gen/org/xtext/example/parser/antlr/internal/InternalEntities.g" 571
RULE_ANY_OTHER : .;


