package app.mistercooper.data.common.di.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

@Qualifier
@Retention(RUNTIME)
public @interface NotAuthOkHttpClient {
}
