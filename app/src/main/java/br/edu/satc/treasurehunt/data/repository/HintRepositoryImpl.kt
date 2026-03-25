package br.edu.satc.treasurehunt.data.repository

import br.edu.satc.treasurehunt.data.datasource.LocalHintDataSource
import br.edu.satc.treasurehunt.domain.model.Hint
import br.edu.satc.treasurehunt.domain.repository.HintRepository

class HintRepositoryImpl(
    private val dataSource: LocalHintDataSource
) : HintRepository {
    override fun getHints(): List<Hint> {
        return dataSource.getHints()
    }
}
