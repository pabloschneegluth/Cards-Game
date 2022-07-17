package com.drpicox.game.card;

import com.drpicox.game.constants.Constants;
import com.drpicox.game.constants.ConstantsCollection;
import com.drpicox.game.constants.ConstantsLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
public class CardConstantsCollection {

    private final ConstantsCollection cardConstantsCollection;

    public CardConstantsCollection(ConstantsLoader constantsLoader) throws IOException, URISyntaxException {
        this.cardConstantsCollection = constantsLoader.loadCollection("card");
    }

    public Constants getByCardName(String cardName) {
        return cardConstantsCollection.getByName(cardName);
    }
}
