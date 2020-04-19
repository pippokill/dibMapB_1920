/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package di.uniba.map.b.adventure.parser;

import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.Command;
import java.util.List;

/**
 *
 * @author pierpaolo
 */
public class Parser {

    private int checkForCommand(String token, List<Command> commands) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getName().equals(token) || commands.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    private int checkForObject(String token, List<AdvObject> obejcts) {
        for (int i = 0; i < obejcts.size(); i++) {
            if (obejcts.get(i).getName().equals(token) || obejcts.get(i).getAlias().contains(token)) {
                return i;
            }
        }
        return -1;
    }

    /* ATTENZIONE: il parser è implementato in modo abbastanza independete dalla lingua mi riconosce solo 
    * frasi semplici del tipo <azione> <oggetto> <oggetto> non permette di utilizzare articoli o preposizioni.
    * L'utilizzo di articoli o preporsizioni lo renderebbero dipendente dalla lingua, o meglio bisognerebbe
    * realizzare un parser per ogni lingua, prevedendo un'iterfaccia/classe astratta Perser e diverse
    * implementazioni per ogni lingua.
    */
    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {
        String cmd = command.toLowerCase().trim();
        String[] tokens = cmd.split("\\s+");
        if (tokens.length > 0) {
            int ic = checkForCommand(tokens[0], commands);
            if (ic > -1) {
                if (tokens.length > 1) {
                    int io = checkForObject(tokens[1], objects);
                    int ioinv = -1;
                    if (io < 0 && tokens.length > 2) {
                        io = checkForObject(tokens[2], objects);
                    }
                    if (io < 0) {
                        ioinv = checkForObject(tokens[1], inventory);
                        if (ioinv < 0 && tokens.length > 2) {
                            ioinv = checkForObject(tokens[2], inventory);
                        }
                    }
                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv));
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
