package org.example;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

public class NodeHandler implements Watcher {

    private static final String TARGET_NODE = "/a";
    private final ZooKeeper zooKeeper;
    private final String processName;
    private Process process;
    private boolean isRunning = false;

    public NodeHandler(String ipPort, String processName) throws IOException, InterruptedException, KeeperException {
        this.processName = processName;
        this.zooKeeper = new ZooKeeper(ipPort, 3000, this);
        this.zooKeeper.addWatch("/", AddWatchMode.PERSISTENT_RECURSIVE);
    }

    @Override
    public void process(WatchedEvent event) {
        String eventPath = event.getPath();
        switch (event.getType()) {
            case NodeCreated -> handleNodeCreated(eventPath);
            case NodeDeleted -> handleNodeDeleted(eventPath);
        }
    }

    private void handleNodeCreated(String path) {
        if (TARGET_NODE.equals(path)) {
            startProcess();
        } else if (path != null && path.startsWith(TARGET_NODE)) {
            System.out.println("Total nodes count: " + getNodeCount());
        }
        System.out.println("New node created: " + path);
    }

    private void handleNodeDeleted(String path) {
        if (TARGET_NODE.equals(path)) {
            stopProcess();
        }
        System.out.println("Node removed: " + path);
    }

    public void printTree(String nodePath, int level) throws InterruptedException, KeeperException {
        if (zooKeeper.exists(nodePath, false) == null) {
            System.out.println("Node '" + nodePath + "' does not exist");
            return;
        }
        printNode(nodePath, level);
    }

    private void printNode(String nodePath, int level) throws KeeperException, InterruptedException {
        System.out.print("   ".repeat(level));
        System.out.println(nodePath);
        List<String> children = zooKeeper.getChildren(nodePath, false);
        for (String child : children) {
            printTree(nodePath + "/" + child, level + 1);
        }
    }
    private int getNodeCount() {
        try {
            if (zooKeeper.exists(TARGET_NODE, true) != null) {
                return zooKeeper.getAllChildrenNumber(TARGET_NODE);
            }
        } catch (KeeperException | InterruptedException e) {
            System.err.println("Error counting nodes");
            e.printStackTrace();
        }
        return 0;
    }

    private void startProcess() {
        if (!isRunning) {
            try {
                process = Runtime.getRuntime().exec(processName);
                isRunning = true;
                System.out.println("Process started: " + processName);
            } catch (IOException e) {
                System.err.println("Failed to start process: " + processName);
                e.printStackTrace();
            }
        }
    }

    private void stopProcess() {
        if (isRunning) {
            process.destroy();
            System.out.println("Process stopped: " + processName);
            isRunning = false;
        }
    }
}
