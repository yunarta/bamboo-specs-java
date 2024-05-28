package com.mobilesolutionworks;

import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.plan.artifact.Artifact;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.builders.task.ScriptTask;
import com.atlassian.bamboo.specs.builders.task.VcsCheckoutTask;
import com.atlassian.bamboo.specs.util.BambooServer;

/**
 * Plan configuration for Bamboo.
 * Learn more on: https://confluence.atlassian.com/display/BAMEAP/Bamboo+Specs
 */
@BambooSpec
public class PlanSpec {

    Project project() {
        return new Project()
                .name("Yunarta")
                .key("YUNARTA");
    }

    Plan createPlan() {
        return new Plan(
                project(),
                "Plan Name", "PLANKEY")
                .description("Plan created from (enter repository url of your plan)")
//                .localRepositories(
//                        gitRepository()
//                )
                .stages(
                        new Stage("Stage 1").jobs(
                                new Job("Job Name", "JOBKEY")
                                        .tasks(
//                                                gitRepositoryCheckoutTask(),
                                                scriptTask()
                                        )
                                        .artifacts(artifact())
                        )
                );
    }

//    VcsRepository gitRepository() {
//        return new GitRepository()
//                .name("your-git-repository")
//                .url("git@bitbucket.org:your-company/your-repository.git")
//                .branch("master");
//    }

    VcsCheckoutTask gitRepositoryCheckoutTask() {
        return new VcsCheckoutTask()
                .addCheckoutOfDefaultRepository();
    }

    ScriptTask scriptTask() {
        return new ScriptTask()
                .inlineBody("mkdir target; echo 'hello world' > target/console.out")
                .interpreterShell();
    }

    Artifact artifact() {
        return new Artifact("Build results")
                .location("target")
                .copyPattern("**/*");
    }
}
