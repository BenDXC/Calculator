# Semantic Versioning Guide

## Overview

This project uses **Semantic Versioning 2.0.0** for all releases.

**Format**: `vMAJOR.MINOR.PATCH` (e.g., v1.0.0, v1.2.3, v2.0.0)

---

## 📖 Semantic Versioning Rules

### Version Format: `vMAJOR.MINOR.PATCH`

- **MAJOR** (v**X**.0.0) - Incompatible API changes, breaking changes
- **MINOR** (v1.**X**.0) - New functionality, backwards-compatible
- **PATCH** (v1.0.**X**) - Bug fixes, backwards-compatible

### Examples

| Version | Change Type | Example |
|---------|-------------|---------|
| v1.0.0 → v**2**.0.0 | **MAJOR** | Breaking change in API |
| v1.0.0 → v1.**1**.0 | **MINOR** | New feature added |
| v1.0.0 → v1.0.**1** | **PATCH** | Bug fix |

---

## 🤖 Automatic Version Bumping

The release workflow **automatically determines** which version to bump based on your commit messages.

### Commit Message Convention

Use **Conventional Commits** format:

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

### Version Bumping Rules

#### MAJOR Version Bump (Breaking Changes)
Triggered by:
- `feat!: ` - Breaking feature
- `fix!: ` - Breaking fix
- `refactor!: ` - Breaking refactor
- Commit body contains `BREAKING CHANGE:`

**Examples**:
```bash
git commit -m "feat!: change API interface for calculations"
git commit -m "fix!: remove deprecated temperature conversion method"

# Or with body:
git commit -m "feat: update calculator API

BREAKING CHANGE: Calculator.calculate() now requires two parameters"
```

**Result**: v1.5.3 → **v2.0.0**

#### MINOR Version Bump (New Features)
Triggered by:
- `feat: ` - New feature
- `feat(scope): ` - Scoped feature

**Examples**:
```bash
git commit -m "feat: add hexadecimal conversion"
git commit -m "feat(ui): add dark mode support"
git commit -m "feat: implement square root calculation"
```

**Result**: v1.5.3 → **v1.6.0**

#### PATCH Version Bump (Fixes & Improvements)
Triggered by:
- `fix: ` - Bug fix
- `docs: ` - Documentation
- `test: ` - Tests
- `refactor: ` - Code refactoring
- `style: ` - Code style
- `chore: ` - Maintenance
- `perf: ` - Performance

**Examples**:
```bash
git commit -m "fix: resolve division by zero error"
git commit -m "docs: update README with examples"
git commit -m "test: add integration tests"
git commit -m "refactor: improve code structure"
```

**Result**: v1.5.3 → **v1.5.4**

---

## 📊 Version Determination Logic

The workflow analyzes **all commits** since the last release:

### 1. Check for Breaking Changes
```bash
If any commit contains:
  - "BREAKING CHANGE"
  - "feat!:", "fix!:", etc.
Then: MAJOR bump
```

### 2. Check for New Features
```bash
Else if any commit starts with:
  - "feat:"
Then: MINOR bump
```

### 3. Default to Patch
```bash
Else:
  - PATCH bump
```

### Priority Order
1. **MAJOR** (highest priority)
2. **MINOR** 
3. **PATCH** (default)

---

## 🎯 Examples

### Scenario 1: Bug Fix

**Commits**:
```
fix: resolve temperature conversion rounding error
test: add tests for temperature edge cases
```

**Result**: v1.0.0 → **v1.0.1** (PATCH)

---

### Scenario 2: New Feature

**Commits**:
```
feat: add binary to decimal conversion
test: add binary conversion tests
docs: update README with binary conversion info
```

**Result**: v1.0.1 → **v1.1.0** (MINOR)

---

### Scenario 3: Breaking Change

**Commits**:
```
feat!: redesign calculator API

BREAKING CHANGE: Calculator now requires initialization
```

**Result**: v1.1.0 → **v2.0.0** (MAJOR)

---

### Scenario 4: Multiple Changes

**Commits**:
```
feat: add hexadecimal conversion
fix: resolve null pointer exception
docs: update API documentation
```

**Result**: v1.1.0 → **v1.2.0** (MINOR - feature takes precedence)

---

### Scenario 5: First Release

**No previous tags found**

**Result**: **v1.0.0** (initial version)

---

## 🔄 Workflow Process

### Automatic Release (on main push)

```
1. Code merged to main
   ↓
2. Workflow fetches all tags
   ↓
3. Finds latest semantic version (e.g., v1.5.3)
   ↓
4. Analyzes commits since last version
   ↓
5. Determines bump type:
   - BREAKING CHANGE → v2.0.0 (MAJOR)
   - feat: → v1.6.0 (MINOR)
   - fix:/docs:/etc. → v1.5.4 (PATCH)
   ↓
6. Creates new version tag
   ↓
7. Generates CHANGELOG.md
   ↓
8. Creates GitHub Release
   ↓
9. Uploads artifacts
```

### Manual Release (specific version)

```
1. Use "Create Version Tag" workflow
   ↓
2. Enter desired version (e.g., v3.0.0)
   ↓
3. Tag created manually
   ↓
4. Release workflow triggers
   ↓
5. Creates release with specified version
```

---

## 💡 Best Practices

### 1. Write Good Commit Messages

**Template**:
```
<type>: <subject>

<body>

<footer>
```

**Example**:
```
feat: add square root calculation

Added Math.sqrt() support to calculator with proper
error handling for negative numbers.

Closes #123
```

### 2. Use Conventional Commits

**Common Types**:
- `feat:` - New feature (→ MINOR bump)
- `fix:` - Bug fix (→ PATCH bump)
- `docs:` - Documentation only
- `test:` - Adding tests
- `refactor:` - Code refactoring
- `style:` - Code formatting
- `perf:` - Performance improvement
- `chore:` - Maintenance tasks

### 3. Indicate Breaking Changes

**Option 1** - Add `!`:
```
feat!: redesign calculator API
```

**Option 2** - Use footer:
```
feat: redesign calculator API

BREAKING CHANGE: Calculator.calculate() signature changed
```

### 4. Scope for Clarity (Optional)
```
feat(ui): add dark mode
fix(calculations): resolve rounding error
docs(readme): update installation instructions
```

---

## 🎨 Changelog Organization

### Automatic Categorization

The CHANGELOG.md automatically organizes commits:

```markdown
### Features (MINOR bumps)
- feat: Add hexadecimal conversion (John Doe, abc123)
- feat: Implement square root (Jane Smith, def456)

### Bug Fixes (PATCH bumps)
- fix: Resolve division by zero (Alice Brown, ghi789)
- fix: Correct temperature conversion (Bob Wilson, jkl012)

### Documentation
- docs: Update API docs (Eve Martinez, mno345)
- docs: Add usage examples (Frank Lee, pqr678)

### Other Changes
- test: Add integration tests (Grace Kim, stu901)
- refactor: Improve code structure (Henry Chen, vwx234)
```

---

## 📈 Version History Example

```
v1.0.0 - Initial release
v1.0.1 - Bug fixes
v1.0.2 - More bug fixes
v1.1.0 - New hexadecimal conversion feature
v1.1.1 - Fix hexadecimal conversion bug
v1.2.0 - New binary conversion feature
v2.0.0 - Breaking change: API redesign
v2.0.1 - Fix API bug
v2.1.0 - New temperature conversion feature
```

---

## 🛠️ Manual Override

If you need a specific version (e.g., for major releases):

### Option 1: Use "Create Version Tag" Workflow
1. Go to Actions → Create Version Tag
2. Enter version: `v2.0.0`
3. Tag is created
4. Release workflow triggers automatically

### Option 2: Git Command
```bash
git tag -a v2.0.0 -m "Release version 2.0.0"
git push origin v2.0.0
# Release workflow triggers automatically
```

---

## ❓ FAQ

### Q: What if I forget to use conventional commits?
**A**: The workflow defaults to PATCH version bump. Your release still works, but the changelog won't be as organized.

### Q: Can I create a v2.0.0 manually?
**A**: Yes! Use the "Create Version Tag" workflow or tag manually with Git.

### Q: What if two features are merged?
**A**: If both use `feat:`, it's still one MINOR bump (v1.0.0 → v1.1.0), not two.

### Q: Can I use v1.0.0-beta?
**A**: The automatic workflow only supports v*.*.* format. For pre-releases, use manual tagging.

### Q: What happens on the first merge to main?
**A**: The workflow creates **v1.0.0** as the first version.

### Q: How do I create a hotfix release?
**A**: 
```bash
# Create hotfix branch
git checkout -b hotfix/critical-fix
# Make fix
git commit -m "fix: resolve critical security issue"
# Merge to main → v1.0.1 created automatically
```

---

## 🎯 Version Bump Examples

### Starting from v1.5.3

| Commit Messages | New Version | Type |
|----------------|-------------|------|
| `fix: bug fix` | v1.5.4 | PATCH |
| `feat: new feature` | v1.6.0 | MINOR |
| `feat!: breaking change` | v2.0.0 | MAJOR |
| `fix: bug` + `feat: feature` | v1.6.0 | MINOR |
| `feat: new` + `feat!: breaking` | v2.0.0 | MAJOR |
| `docs: update` + `test: add` | v1.5.4 | PATCH |

---

## 🌟 Benefits

### For Developers
- ✅ Automatic version management
- ✅ Clear versioning rules
- ✅ No manual version tracking

### For Users
- ✅ Understand impact of updates
- ✅ Know when breaking changes occur
- ✅ Trust in versioning consistency

### For Project
- ✅ Industry-standard versioning
- ✅ Professional release management
- ✅ Clear version history

---

## 📚 References

- [Semantic Versioning 2.0.0](https://semver.org/)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [Keep a Changelog](https://keepachangelog.com/)

---

## ✅ Summary

**Versioning Strategy**: Semantic Versioning 2.0.0
**Automation**: Fully automated based on commit messages
**Override**: Manual tagging available when needed
**Benefits**: Professional, predictable, automatic

🎉 **Zero manual version management required!**

---

**Last Updated**: February 8, 2026  
**Status**: ✅ Implemented and Active
