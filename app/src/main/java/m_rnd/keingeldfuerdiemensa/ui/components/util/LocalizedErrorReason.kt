package m_rnd.keingeldfuerdiemensa.ui.components.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import m_rnd.keingeldfuerdiemensa.R
import m_rnd.keingeldfuerdiemensa.entities.util.ErrorReason

@Composable
fun ErrorReason.getLocalizedReason(): String {
    return when (this) {
        is ErrorReason.Api.ErrorResponse -> stringResource(R.string.error_reason_api_error)
        is ErrorReason.Api.NoConnection -> stringResource(R.string.error_reason_api_no_connection)
        is ErrorReason.Api.Other -> stringResource(R.string.error_reason_api_other)
        is ErrorReason.Db.EmptyResult -> stringResource(R.string.error_reason_db_empty_result)
        is ErrorReason.Unknown -> stringResource(R.string.error_reason_unknown)
        is ErrorReason.UseCaseException -> stringResource(R.string.error_reason_app, exception.toString())
    }
}