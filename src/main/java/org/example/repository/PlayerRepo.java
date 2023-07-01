package org.example.repository;
import org.example.models.*;

import java.util.List;

public interface PlayerRepo {
    StringBuffer getCurrentPlayerStats();
    void endGame();
}
