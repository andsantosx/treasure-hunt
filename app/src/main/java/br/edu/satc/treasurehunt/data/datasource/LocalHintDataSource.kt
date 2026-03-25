package br.edu.satc.treasurehunt.data.datasource

import br.edu.satc.treasurehunt.domain.model.Hint

class LocalHintDataSource {
    fun getHints(): List<Hint> {
        return listOf(
            Hint(1, "O que é que tem dentes, mas não morde?", "Pente"),
            Hint(2, "O que é que tem capa, mas não é super-herói?", "Livro"),
            Hint(3, "O que é que corre, mas não tem pernas?", "Rio")
        )
    }
}
