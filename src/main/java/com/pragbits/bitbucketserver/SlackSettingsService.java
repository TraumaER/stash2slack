package com.pragbits.bitbucketserver;

import javax.annotation.Nonnull;

import com.atlassian.bitbucket.repository.Repository;

public interface SlackSettingsService {

	@Nonnull
	SlackSettings getSlackSettings(@Nonnull Repository repository);

	@Nonnull
	SlackSettings setSlackSettings(@Nonnull Repository repository, @Nonnull SlackSettings settings);

}
