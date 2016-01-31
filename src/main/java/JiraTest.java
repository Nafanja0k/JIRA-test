import com.atlassian.jira.rest.client.domain.Issue;

import java.util.concurrent.ExecutionException;

/**
 * Created by admin on 31.01.2016.
 */
public class JiraTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Issue issue;
        JiraClient jc = new JiraClient();
        jc.init();
        issue = jc.getPlainJiraIssue("51462");
        System.out.println(jc);
        System.out.println(issue);

    }
}
