package com.j2assembly.compiler.blocks.blocks;

import com.j2assembly.compiler.blocks.CodeBlock;
import com.j2assembly.compiler.tokenising.Token;
import com.j2assembly.compiler.tokenising.TokenType;

import java.util.ArrayList;
import java.util.List;

public class WhileLoopBlock extends CodeBlock {

    private List<Token> whileList, function;

    public WhileLoopBlock(List<Token> tokens) {
        super(TokenType.TOKEN_WHILE, tokens);
    }

    @Override
    protected void extractTokenData(List<Token> tokens) {
        int brace = 0;
        whileList = new ArrayList<>();
        for (Token token : tokens) {
            whileList.add(token);
            if (token.getTokenType() == TokenType.TOKEN_LBRACKET) {
                brace++;
            } else if (token.getTokenType() == TokenType.TOKEN_RBRACKET) {
                brace--;
            }
            if (brace == 0) {
                break;
            }
        }
        tokens.removeAll(whileList);

        function = new ArrayList<>();
        for (Token token : tokens) {
            function.add(token);
            if (token.getTokenType() == TokenType.TOKEN_LCBRACKET) {
                brace++;
            } else if (token.getTokenType() == TokenType.TOKEN_RCBRACKET) {
                brace--;
            }
            if (brace == 0) {
                break;
            }
        }
    }

    @Override
    public String generateCodeStart() {
        String code = "while (";

        // COMPARISION HERE
        return code + ") {";
    }

    @Override
    public String generateCodeEnd() {
        return "}";
    }
}
