import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * Created by admin on 31.01.2016.
 */
public class JiraClient {
    private JiraRestClient jiraClient;
    private String user;
    private String password;
    /**
     * Initialize connection to Jira.
     */
    public void init() {

        URI uri = null;
        JiraRestClientFactory factory = null;

        try {

            uri = new URI("https://jira.atlassian.com");
            //LOG.debug("Trying to access " + uri);
            setUser("sanja.nafanja@gmail.com");
            setPassword("1357924680");
            factory = new AsynchronousJiraRestClientFactory();
            JiraRestClient jiraClient = factory.createWithBasicHttpAuthentication(uri, getUser(), getPassword());
            setJiraClient(jiraClient);

        } catch (URISyntaxException e) {
            System.out.println(e);
            //throw new JiraClientException("Could not establish connection to " + getJiraUrl(), e);
        }
    }


    /**
     * Get the Jira issue defined by the given ID.
     *
     * @param issueId The issue's ID.
     * @return The Jira issue.
     */
    public Issue getPlainJiraIssue(String issueId) throws InterruptedException, ExecutionException {

        IssueRestClient issueClient = null;
        Issue issue = null;
        Promise<Issue> promise = null;

        issueClient = getJiraClient().getIssueClient();
        promise = issueClient.getIssue(issueId);
        issue = promise.get();

        return issue;
    }

    public void getIssues() {
                    /* Search for issues */
        Issue.SearchResult sr = jiraClient.searchIssues("assignee=batman");
        System.out.println("Total: " + sr.total);
        for (Issue i : sr.issues)
            System.out.println("Result: " + i);


    }


    public JiraRestClient getJiraClient() {
        return jiraClient;
    }

    public void setJiraClient(JiraRestClient jiraClient) {
        this.jiraClient = jiraClient;
    }

    private void setUser(String user) {
        this.user = user;
    }
    public String getUser() {
        return user;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public  String getPassword() {
        return password;
    }
}

