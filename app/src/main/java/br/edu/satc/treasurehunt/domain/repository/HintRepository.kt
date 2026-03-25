package br.edu.satc.treasurehunt.domain.repository

import br.edu.satc.treasurehunt.domain.model.Hint

interface HintRepository {
    fun getHints(): List<Hint>
}
