package com.plohoy.generator.model.tool.impl.git;

import com.plohoy.generator.model.Source;
import com.plohoy.generator.model.codeentity.CodeEntity;
import com.plohoy.generator.model.file.FileType;
import com.plohoy.generator.model.file.SimpleSourceFile;
import com.plohoy.generator.model.tool.AbstractTool;

import static com.plohoy.generator.util.codegenhelper.codetemplate.CodeTemplate.SLASH;

public class GitTool extends AbstractTool {
    public GitTool(String version) {
        super(version);
    }

    public GitTool() {
    }

    public Source generateCode(Source source) {
        source.getSourceData()
                .get(FileType.EXTERNAL_FILE)
                .add(SimpleSourceFile.builder()
                        .path(source.getPath() + source.getArtifactName() + SLASH)
                        .fileName(".gitignore")
                        .data(getGitIgnoreCode())
                        .build());
        return source;
    }

    private GitIgnoreEntity getGitIgnoreCode() {
        return GitIgnoreEntity.builder()
                .value("# Created by .ignore support plugin (hsz.mobi)\n" +
                        "### JDeveloper template\n" +
                        "# default application storage directory used by the IDE Performance Cache feature\n" +
                        ".data/\n" +
                        "\n" +
                        "# used for ADF styles caching\n" +
                        "temp/\n" +
                        "\n" +
                        "# default output directories\n" +
                        "classes/\n" +
                        "deploy/\n" +
                        "javadoc/\n" +
                        "\n" +
                        "# lock file, a part of Oracle Credential Store Framework\n" +
                        "cwallet.sso.lck\n" +
                        "### Java template\n" +
                        "# Compiled class file\n" +
                        "*.class\n" +
                        "\n" +
                        "# Log file\n" +
                        "*.log\n" +
                        "\n" +
                        "# BlueJ files\n" +
                        "*.ctxt\n" +
                        "\n" +
                        "# Mobile Tools for Java (J2ME)\n" +
                        ".mtj.tmp/\n" +
                        "\n" +
                        "# Package Files #\n" +
                        "*.jar\n" +
                        "*.war\n" +
                        "*.nar\n" +
                        "*.ear\n" +
                        "*.zip\n" +
                        "*.tar.gz\n" +
                        "*.rar\n" +
                        "\n" +
                        "# virtual machine crash logs, see http://www.java.com/en/download/help/error_hotspot.xml\n" +
                        "hs_err_pid*\n" +
                        "\n" +
                        "### JEnv template\n" +
                        "# JEnv local Java version configuration file\n" +
                        ".java-version\n" +
                        "\n" +
                        "# Used by previous versions of JEnv\n" +
                        ".jenv-version\n" +
                        "\n" +
                        "### JetBrains template\n" +
                        "# Covers JetBrains IDEs: IntelliJ, RubyMine, PhpStorm, AppCode, PyCharm, CLion, Android Studio and WebStorm\n" +
                        "# Reference: https://intellij-support.jetbrains.com/hc/en-us/articles/206544839\n" +
                        "\n" +
                        "# User-specific stuff\n" +
                        ".idea/**/workspace.xml\n" +
                        ".idea/**/tasks.xml\n" +
                        ".idea/**/usage.statistics.xml\n" +
                        ".idea/**/dictionaries\n" +
                        ".idea/**/shelf\n" +
                        "\n" +
                        "# Generated files\n" +
                        ".idea/**/contentModel.xml\n" +
                        "\n" +
                        "# Sensitive or high-churn files\n" +
                        ".idea/**/dataSources/\n" +
                        ".idea/**/dataSources.ids\n" +
                        ".idea/**/dataSources.local.xml\n" +
                        ".idea/**/sqlDataSources.xml\n" +
                        ".idea/**/dynamic.xml\n" +
                        ".idea/**/uiDesigner.xml\n" +
                        ".idea/**/dbnavigator.xml\n" +
                        "\n" +
                        "# Gradle\n" +
                        ".idea/**/gradle.xml\n" +
                        ".idea/**/libraries\n" +
                        "\n" +
                        "# Gradle and Maven with auto-import\n" +
                        "# When using Gradle or Maven with auto-import, you should exclude module files,\n" +
                        "# since they will be recreated, and may cause churn.  Uncomment if using\n" +
                        "# auto-import.\n" +
                        "# .idea/modules.xml\n" +
                        "# .idea/*.iml\n" +
                        "# .idea/modules\n" +
                        "# *.iml\n" +
                        "# *.ipr\n" +
                        "\n" +
                        "# CMake\n" +
                        "cmake-build-*/\n" +
                        "\n" +
                        "# Mongo Explorer plugin\n" +
                        ".idea/**/mongoSettings.xml\n" +
                        "\n" +
                        "# File-based project format\n" +
                        "*.iws\n" +
                        "\n" +
                        "# IntelliJ\n" +
                        "out/\n" +
                        "\n" +
                        "# mpeltonen/sbt-idea plugin\n" +
                        ".idea_modules/\n" +
                        "\n" +
                        "# JIRA plugin\n" +
                        "atlassian-ide-plugin.xml\n" +
                        "\n" +
                        "# Cursive Clojure plugin\n" +
                        ".idea/replstate.xml\n" +
                        "\n" +
                        "# Crashlytics plugin (for Android Studio and IntelliJ)\n" +
                        "com_crashlytics_export_strings.xml\n" +
                        "crashlytics.properties\n" +
                        "crashlytics-build.properties\n" +
                        "fabric.properties\n" +
                        "\n" +
                        "# Editor-based Rest Client\n" +
                        ".idea/httpRequests\n" +
                        "\n" +
                        "# Android studio 3.1+ serialized cache file\n" +
                        ".idea/caches/build_file_checksums.ser\n" +
                        "\n" +
                        "### SublimeText template\n" +
                        "# Cache files for Sublime Text\n" +
                        "*.tmlanguage.cache\n" +
                        "*.tmPreferences.cache\n" +
                        "*.stTheme.cache\n" +
                        "\n" +
                        "# Workspace files are user-specific\n" +
                        "*.sublime-workspace\n" +
                        "\n" +
                        "# Project files should be checked into the repository, unless a significant\n" +
                        "# proportion of contributors will probably not be using Sublime Text\n" +
                        "# *.sublime-project\n" +
                        "\n" +
                        "# SFTP configuration file\n" +
                        "sftp-config.json\n" +
                        "\n" +
                        "# Package control specific files\n" +
                        "Package Control.last-run\n" +
                        "Package Control.ca-list\n" +
                        "Package Control.ca-bundle\n" +
                        "Package Control.system-ca-bundle\n" +
                        "Package Control.cache/\n" +
                        "Package Control.ca-certs/\n" +
                        "Package Control.merged-ca-bundle\n" +
                        "Package Control.user-ca-bundle\n" +
                        "oscrypto-ca-bundle.crt\n" +
                        "bh_unicode_properties.cache\n" +
                        "\n" +
                        "# Sublime-github package stores a github token in this file\n" +
                        "# https://packagecontrol.io/packages/sublime-github\n" +
                        "GitHub.sublime-settings\n" +
                        "\n" +
                        "### Maven template\n" +
                        "target/\n" +
                        "pom.xml.tag\n" +
                        "pom.xml.releaseBackup\n" +
                        "pom.xml.versionsBackup\n" +
                        "pom.xml.next\n" +
                        "release.properties\n" +
                        "dependency-reduced-pom.xml\n" +
                        "buildNumber.properties\n" +
                        ".mvn/timing.properties\n" +
                        ".mvn/wrapper/maven-wrapper.jar\n" +
                        "\n" +
                        "### VisualStudioCode template\n" +
                        ".vscode/*\n" +
                        "!.vscode/settings.json\n" +
                        "!.vscode/tasks.json\n" +
                        "!.vscode/launch.json\n" +
                        "!.vscode/extensions.json\n" +
                        "\n" +
                        "### Eclipse template\n" +
                        ".metadata\n" +
                        "bin/\n" +
                        "tmp/\n" +
                        "*.tmp\n" +
                        "*.bak\n" +
                        "*.swp\n" +
                        "*~.nib\n" +
                        "local.properties\n" +
                        ".settings/\n" +
                        ".loadpath\n" +
                        ".recommenders\n" +
                        "\n" +
                        "# External tool builders\n" +
                        ".externalToolBuilders/\n" +
                        "\n" +
                        "# Locally stored \"Eclipse launch configurations\"\n" +
                        "*.launch\n" +
                        "\n" +
                        "# PyDev specific (Python IDE for Eclipse)\n" +
                        "*.pydevproject\n" +
                        "\n" +
                        "# CDT-specific (C/C++ Development Tooling)\n" +
                        ".cproject\n" +
                        "\n" +
                        "# CDT- autotools\n" +
                        ".autotools\n" +
                        "\n" +
                        "# Java annotation processor (APT)\n" +
                        ".factorypath\n" +
                        "\n" +
                        "# PDT-specific (PHP Development Tools)\n" +
                        ".buildpath\n" +
                        "\n" +
                        "# sbteclipse plugin\n" +
                        ".target\n" +
                        "\n" +
                        "# Tern plugin\n" +
                        ".tern-project\n" +
                        "\n" +
                        "# TeXlipse plugin\n" +
                        ".texlipse\n" +
                        "\n" +
                        "# STS (Spring AbstractTool Suite)\n" +
                        ".springBeans\n" +
                        "\n" +
                        "# Code Recommenders\n" +
                        ".recommenders/\n" +
                        "\n" +
                        "# Annotation Processing\n" +
                        ".apt_generated/\n" +
                        "\n" +
                        "# Scala IDE specific (Scala & Java development for Eclipse)\n" +
                        ".cache-main\n" +
                        ".scala_dependencies\n" +
                        ".worksheet\n" +
                        "\n" +
                        "### MicrosoftOffice template\n" +
                        "\n" +
                        "# Word temporary\n" +
                        "~$*.doc*\n" +
                        "\n" +
                        "# Word Auto Backup File\n" +
                        "Backup of *.doc*\n" +
                        "\n" +
                        "# Excel temporary\n" +
                        "~$*.xls*\n" +
                        "\n" +
                        "# Excel Backup File\n" +
                        "*.xlk\n" +
                        "\n" +
                        "# PowerPoint temporary\n" +
                        "~$*.ppt*\n" +
                        "\n" +
                        "# Visio autosave temporary files\n" +
                        "*.~vsd*\n" +
                        "\n" +
                        "### TortoiseGit template\n" +
                        "# Project-level settings\n" +
                        "/.tgitconfig\n" +
                        "\n" +
                        "### NotepadPP template\n" +
                        "# Notepad++ backups #\n" +
                        "\n" +
                        "### Linux template\n" +
                        "*~\n" +
                        "\n" +
                        "# temporary files which can be created if a process still has a handle open of a deleted file\n" +
                        ".fuse_hidden*\n" +
                        "\n" +
                        "# KDE directory preferences\n" +
                        ".directory\n" +
                        "\n" +
                        "# Linux trash folder which might appear on any partition or disk\n" +
                        ".Trash-*\n" +
                        "\n" +
                        "# .nfs files are created when an open file is removed but is still being accessed\n" +
                        ".nfs*\n" +
                        "\n" +
                        "### Example user template template\n" +
                        "### Example user template\n" +
                        "\n" +
                        "# IntelliJ project files\n" +
                        ".idea\n" +
                        "*.iml\n" +
                        "out\n" +
                        "gen\n" +
                        "### Archives template\n" +
                        "# It's better to unpack these files and commit the raw source because\n" +
                        "# git has its own built in compression methods.\n" +
                        "*.7z\n" +
                        "*.gz\n" +
                        "*.tgz\n" +
                        "*.bzip\n" +
                        "*.bz2\n" +
                        "*.xz\n" +
                        "*.lzma\n" +
                        "*.cab\n" +
                        "\n" +
                        "# Packing-only formats\n" +
                        "*.iso\n" +
                        "*.tar\n" +
                        "\n" +
                        "# Package management formats\n" +
                        "*.dmg\n" +
                        "*.xpi\n" +
                        "*.gem\n" +
                        "*.egg\n" +
                        "*.deb\n" +
                        "*.rpm\n" +
                        "*.msi\n" +
                        "*.msm\n" +
                        "*.msp\n" +
                        "\n" +
                        "### Gradle template\n" +
                        ".gradle\n" +
                        "/build/\n" +
                        "\n" +
                        "# Ignore Gradle GUI config\n" +
                        "gradle-app.setting\n" +
                        "\n" +
                        "# Avoid ignoring Gradle wrapper jar file (.jar files are usually ignored)\n" +
                        "!gradle-wrapper.jar\n" +
                        "\n" +
                        "# Cache of project\n" +
                        ".gradletasknamecache\n" +
                        "\n" +
                        "# # Work around https://youtrack.jetbrains.com/issue/IDEA-116898\n" +
                        "# gradle/wrapper/gradle-wrapper.properties\n" +
                        "\n" +
                        "### macOS template\n" +
                        "# General\n" +
                        ".DS_Store\n" +
                        ".AppleDouble\n" +
                        ".LSOverride\n" +
                        "\n" +
                        "# Icon must end with two \\r\n" +
                        "Icon\n" +
                        "\n" +
                        "# Thumbnails\n" +
                        "._*\n" +
                        "\n" +
                        "# Files that might appear in the root of a volume\n" +
                        ".DocumentRevisions-V100\n" +
                        ".fseventsd\n" +
                        ".Spotlight-V100\n" +
                        ".TemporaryItems\n" +
                        ".Trashes\n" +
                        ".VolumeIcon.icns\n" +
                        ".com.apple.timemachine.donotpresent\n" +
                        "\n" +
                        "# Directories potentially created on remote AFP share\n" +
                        ".AppleDB\n" +
                        ".AppleDesktop\n" +
                        "Network Trash Folder\n" +
                        "Temporary Items\n" +
                        ".apdisk\n" +
                        "\n" +
                        "### Backup template\n" +
                        "*.gho\n" +
                        "*.ori\n" +
                        "*.orig\n" +
                        "\n" +
                        "### JENKINS_HOME template\n" +
                        "#Learn more about Jenkins and JENKINS_HOME directory for which this file is intended.\n" +
                        "#  http://jenkins-ci.org/\n" +
                        "#  https://wiki.jenkins-ci.org/display/JENKINS/Administering+Jenkins\n" +
                        "\n" +
                        "#ignore all JENKINS_HOME except jobs directory, root xml config, and .gitignore file\n" +
                        "#/*\n" +
                        "!/jobs\n" +
                        "!/.gitignore\n" +
                        "!/*.xml\n" +
                        "\n" +
                        "#ignore all files in jobs subdirectories except for folders\n" +
                        "#note: git doesn't track folders, only file content\n" +
                        "jobs/**\n" +
                        "!jobs/**/\n" +
                        "\n" +
                        "#uncomment the following line to save next build numbers with config\n" +
                        "#!jobs/**/nextBuildNumber\n" +
                        "\n" +
                        "#exclude only config.xml files in repository subdirectories\n" +
                        "!config.xml\n" +
                        "\n" +
                        "#don't track workspaces (when users build on the master)\n" +
                        "jobs/**/*workspace\n" +
                        "\n" +
                        "#as a result only settings and job config.xml files in JENKINS_HOME will be tracked by git\n" +
                        "\n" +
                        "### Windows template\n" +
                        "# Windows thumbnail cache files\n" +
                        "Thumbs.db\n" +
                        "Thumbs.db:encryptable\n" +
                        "ehthumbs.db\n" +
                        "ehthumbs_vista.db\n" +
                        "\n" +
                        "# Dump file\n" +
                        "*.stackdump\n" +
                        "\n" +
                        "# Folder config file\n" +
                        "[Dd]esktop.ini\n" +
                        "\n" +
                        "# Recycle Bin used on file shares\n" +
                        "$RECYCLE.BIN/\n" +
                        "\n" +
                        "# Windows Installer files\n" +
                        "*.msix\n" +
                        "\n" +
                        "# Windows shortcuts\n" +
                        "*.lnk\n" +
                        "\n" +
                        "### NetBeans template\n" +
                        "**/nbproject/private/\n" +
                        "**/nbproject/Makefile-*.mk\n" +
                        "**/nbproject/Package-*.bash\n" +
                        "build/\n" +
                        "nbbuild/\n" +
                        "dist/\n" +
                        "nbdist/\n" +
                        ".nb-gradle/\n" +
                        "\n" +
                        "### Vim template\n" +
                        "# Swap\n" +
                        "[._]*.s[a-v][a-z]\n" +
                        "[._]*.sw[a-p]\n" +
                        "[._]s[a-rt-v][a-z]\n" +
                        "[._]ss[a-gi-z]\n" +
                        "[._]sw[a-p]\n" +
                        "\n" +
                        "# Session\n" +
                        "Session.vim\n" +
                        "\n" +
                        "# Temporary\n" +
                        ".netrwhist\n" +
                        "# Auto-generated tag files\n" +
                        "tags\n" +
                        "# Persistent undo\n" +
                        "[._]*.un~\n")
                .build();
    }
}
