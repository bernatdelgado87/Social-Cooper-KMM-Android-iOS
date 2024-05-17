package app.mistercooper.social.navigation

import androidx.compose.runtime.Composable
import app.mistercooper.ui.comment.CommentsBottomSheet
import app.mistercooper.ui.common.navigation.ArgumentNavigatorWrapper
import app.mistercooper.ui.common.navigation.BottomSheetRoute
import app.mistercooper.ui.common.navigation.BottomSheetRoute.COMMENTS
import app.mistercooper.ui.common.navigation.CustomNavigator

class CustomNavigatorImpl: CustomNavigator {
    @Composable
    override fun showBottomSheet(bottomSheetRoute: BottomSheetRoute, args: Map<String, ArgumentNavigatorWrapper>) {
        when(bottomSheetRoute){
            COMMENTS -> {
                val postId = (args["postId"] as? ArgumentNavigatorWrapper.LongArg)?.value ?: throw Exception("No value for Id")
                val writeNow = (args["writeNow"] as? ArgumentNavigatorWrapper.BooleanArg)?.value ?: false
                val onDismiss = (args["onDismiss"] as? ArgumentNavigatorWrapper.FunctionArg)?.value ?: throw Exception("No value for onDismiss")

                CommentsBottomSheet(
                    postId = postId,
                    writeNow = writeNow,
                    onDismiss = onDismiss
                )
            }
        }
    }

}