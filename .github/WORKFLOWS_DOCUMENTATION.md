# GitHub Workflows Documentation

This repository contains four GitHub Actions workflows for automated building, testing, tagging, and releasing.

## Workflows Overview

| Workflow | Trigger | Purpose | Status |
|----------|---------|---------|--------|
| **Build and Test** | Push/PR to main, develop, feature branches | CI/CD validation | ✅ Active |
| **Create Version Tag** | Manual dispatch | Create version tags | ✅ Active |
| **Create Release** | Push of version tags (v*.*.*) | Create releases from tags | ✅ Active |
| **Release on Main Push** | Push to main branch | Automatic release creation | ✅ Active |

---

## 1. Build and Test Workflow

**File**: `.github/workflows/build.yml`

### Trigger
```yaml
on:
  push:
    branches: [main, develop, feature/**, hotfix/**]
  pull_request:
    branches: [main, develop]
```

### What It Does
- ✅ Checks out the repository
- ✅ Sets up JDK 11 with Maven caching
- ✅ Builds the project with `mvn clean package`
- ✅ Runs all 225 tests
- ✅ Uploads JAR artifact (retained for 7 days)
- ✅ Uploads test results (retained for 7 days)

### Use Case
Continuous Integration - ensures code quality on every push and pull request.

### Example Output
```
✅ Build: SUCCESS
✅ Tests: 225/225 PASSED
📦 Artifacts: calculator.jar uploaded
```

---

## 2. Create Version Tag Workflow

**File**: `.github/workflows/tag.yml`

### Trigger
```yaml
on:
  workflow_dispatch:
    inputs:
      version: (e.g., v1.0.0)
      message: (optional tag message)
```

### What It Does
- ✅ Validates version format (must match v*.*.*)
- ✅ Checks if tag already exists
- ✅ Creates annotated Git tag
- ✅ Pushes tag to repository
- ✅ Provides tag URL in output

### How to Use

1. Go to **Actions** tab in GitHub
2. Select **"Create Version Tag"** workflow
3. Click **"Run workflow"**
4. Enter version (e.g., `v1.0.0`)
5. Optionally add a message
6. Click **"Run workflow"**

### Example
```bash
Input: version = "v1.2.3"
Output: ✅ Tag v1.2.3 created and pushed
```

---

## 3. Create Release Workflow

**File**: `.github/workflows/release.yml`

### Trigger
```yaml
on:
  push:
    tags: ['v*.*.*']
  workflow_dispatch:
    inputs:
      tag: (e.g., v1.0.0)
      prerelease: (boolean)
```

### What It Does
- ✅ Builds the project
- ✅ Runs all tests
- ✅ Generates changelog from git commits
- ✅ Creates GitHub Release
- ✅ Uploads JAR file to release
- ✅ Publishes to GitHub Packages

### Automatic Workflow (Recommended)
```bash
# This workflow triggers automatically when you push a tag
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0

# GitHub Actions automatically:
# 1. Builds the project
# 2. Runs tests
# 3. Creates release with changelog
# 4. Uploads calculator-1.0.0.jar
```

### Manual Workflow
1. Go to **Actions** tab
2. Select **"Create Release"**
3. Click **"Run workflow"**
4. Enter tag name and prerelease option
5. Click **"Run workflow"**

### Changelog Generation
The workflow automatically generates a changelog by comparing commits between the current tag and the previous tag:

```markdown
## Changes since v1.0.0
- Add new feature (John Doe)
- Fix bug in calculation (Jane Smith)
- Update documentation (Alice Brown)
```

---

## 4. Release on Main Push Workflow ⭐ NEW

**File**: `.github/workflows/release-on-main.yml`

### Trigger
```yaml
on:
  push:
    branches: [main]
```

### What It Does
- ✅ **Automatically triggers** when code is pushed to main
- ✅ Builds and tests the project
- ✅ **Auto-generates version**: `v{YEAR}.{MONTH}.{DAY}-build.{BUILD_NUMBER}`
  - Example: `v2026.02.08-build.5`
- ✅ Creates Git tag with generated version
- ✅ Creates GitHub Release
- ✅ Generates changelog from commits
- ✅ Uploads **two JAR files**:
  - `calculator-{version}.jar` (versioned artifact)
  - `calculator-latest.jar` (always latest build)
- ✅ Publishes to GitHub Packages
- ✅ Creates comprehensive release summary

### Version Format
```
v{YEAR}.{MONTH}.{DAY}-build.{BUILD_NUMBER}

Examples:
- v2026.02.08-build.1  (first build on Feb 8, 2026)
- v2026.02.08-build.5  (fifth build on Feb 8, 2026)
- v2026.03.15-build.12 (12th build on Mar 15, 2026)
```

### Use Case
**Continuous Deployment** - Every merge to main automatically creates a release.

Perfect for:
- Automated release pipelines
- Continuous delivery to production
- Tracking every production deployment
- Always having the latest build available

### Example Workflow
```bash
# Developer workflow:
1. Create feature branch: git checkout -b feature/new-calculation
2. Make changes and commit
3. Push and create PR to main
4. PR is reviewed and approved
5. Merge PR to main

# Automatic process (no manual intervention):
6. ✅ Release workflow triggers automatically
7. ✅ Builds and tests (225 tests)
8. ✅ Creates tag: v2026.02.08-build.5
9. ✅ Creates release with changelog
10. ✅ Uploads calculator-2026.02.08.5.jar
11. ✅ Uploads calculator-latest.jar
12. ✅ Release is live! 🚀
```

### Release Artifacts
Each release includes:
- **Versioned JAR**: `calculator-{YYYY.MM.DD.BUILD}.jar`
- **Latest JAR**: `calculator-latest.jar` (always points to newest build)
- **Changelog**: All commits since last release
- **Test Results**: Confirmation that all 225 tests passed
- **Build Metadata**: Commit SHA, build date, build number

---

## Workflow Comparison

### When to Use Each Workflow

| Scenario | Recommended Workflow |
|----------|---------------------|
| **Automatic release on every main push** | ✅ Release on Main Push |
| **Manual release with specific version** | Create Tag → Create Release |
| **Pre-release/beta testing** | Create Release (manual, mark as prerelease) |
| **CI/CD testing only** | Build and Test |
| **Hotfix release** | Create Tag (v1.0.1) → Auto Release |

### Workflow Flow Diagrams

#### Automatic Release Flow
```
Push to main
    ↓
Release on Main Push Workflow
    ↓
Build & Test
    ↓
Auto-generate version (v2026.02.08-build.5)
    ↓
Create tag
    ↓
Create GitHub Release
    ↓
Upload JARs (versioned + latest)
    ↓
✅ Release Published
```

#### Manual Versioned Release Flow
```
Run "Create Version Tag" workflow
    ↓
Enter version (v1.0.0)
    ↓
Tag created
    ↓
"Create Release" workflow auto-triggers
    ↓
Build & Test
    ↓
Generate changelog
    ↓
Create GitHub Release
    ↓
Upload JAR
    ↓
✅ Release Published
```

---

## Configuration

### Required Permissions
All workflows require these permissions (already configured):

```yaml
permissions:
  contents: write    # Create releases and tags
  packages: write    # Publish to GitHub Packages
```

### Secrets
No additional secrets required! Workflows use:
- `${{ secrets.GITHUB_TOKEN }}` - Automatically provided by GitHub

### Maven Configuration
The `pom.xml` is configured with:
- Main class: `calculator.src.main.java.com.calculator.run`
- GitHub Packages repository
- JaCoCo for code coverage

---

## Best Practices

### For Main Branch Releases (Automatic)
✅ **DO**:
- Merge only reviewed and approved PRs to main
- Ensure all tests pass before merging
- Write clear, descriptive commit messages (they appear in changelog)
- Use conventional commit format: `feat:`, `fix:`, `docs:`, etc.

❌ **DON'T**:
- Push directly to main without PR review
- Merge failing builds
- Use generic commit messages like "updates"

### For Manual Releases (Tagged)
✅ **DO**:
- Use semantic versioning (v1.0.0, v1.1.0, v2.0.0)
- Create release branches for major versions
- Document breaking changes in release notes
- Test thoroughly before tagging

❌ **DON'T**:
- Delete or modify existing tags
- Skip version numbers
- Create tags without testing

---

## Monitoring Releases

### View All Releases
```
https://github.com/{owner}/{repo}/releases
```

### View Workflow Runs
```
https://github.com/{owner}/{repo}/actions
```

### Download Latest Release
```bash
# Get the latest JAR (always up to date)
wget https://github.com/{owner}/{repo}/releases/latest/download/calculator-latest.jar

# Get specific version
wget https://github.com/{owner}/{repo}/releases/download/v2026.02.08-build.5/calculator-2026.02.08.5.jar
```

---

## Troubleshooting

### Build Failures
If a build fails:
1. Check the Actions tab for error logs
2. Review test failures in the Surefire reports
3. Fix issues locally and push again
4. Workflow will re-run automatically

### Tag Already Exists
If you get "tag already exists":
1. Check existing tags: `git tag -l`
2. Use a new version number
3. Or delete the tag if necessary:
   ```bash
   git tag -d v1.0.0
   git push origin :refs/tags/v1.0.0
   ```

### Release Not Created
If release workflow doesn't trigger:
1. Verify tag format matches `v*.*.*`
2. Check workflow file is on main branch
3. Review Actions tab for error messages
4. Ensure you have write permissions

---

## Examples

### Example 1: Feature Development
```bash
git checkout -b feature/add-calculator-function
# Make changes
git commit -m "feat: add square root calculation"
git push origin feature/add-calculator-function
# Create PR → Merge to main
# ✅ Automatic release created: v2026.02.08-build.6
```

### Example 2: Hotfix
```bash
git checkout -b hotfix/fix-division-bug
# Fix the bug
git commit -m "fix: resolve division by zero issue"
git push origin hotfix/fix-division-bug
# Create PR → Merge to main
# ✅ Automatic release created with fix
```

### Example 3: Major Version Release
```bash
# Use manual tag workflow for semantic versioning
# Actions → Create Version Tag
# Input: v2.0.0
# ✅ Tag created
# ✅ Release workflow triggers automatically
# ✅ Release v2.0.0 published
```

---

## Summary

🚀 **Four powerful workflows** for complete CI/CD:

1. **Build and Test** - Validates every change
2. **Create Version Tag** - Manual version tagging
3. **Create Release** - Creates releases from tags
4. **Release on Main Push** - Automatic releases ⭐

Choose the workflow that fits your needs:
- **Fully Automated**: Use "Release on Main Push"
- **Controlled Releases**: Use "Create Tag" + "Create Release"
- **Hybrid**: Mix both approaches

All workflows are production-ready and fully tested! 🎉
