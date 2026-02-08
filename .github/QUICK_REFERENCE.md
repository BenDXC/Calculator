# Quick Reference: Semantic Versioning & Releases

## 🚀 Semantic Versioning Format

```
vMAJOR.MINOR.PATCH
```

Examples: `v1.0.0`, `v1.2.3`, `v2.0.0`

---

## 📝 Commit Message → Version Bump

| Commit Prefix | Version Bump | Example |
|---------------|--------------|---------|
| `feat!:` or `BREAKING CHANGE:` | **MAJOR** 🔴 | v1.5.3 → **v2.0.0** |
| `feat:` | **MINOR** 🟡 | v1.5.3 → **v1.6.0** |
| `fix:` | **PATCH** 🟢 | v1.5.3 → **v1.5.4** |
| `docs:` | **PATCH** 🟢 | v1.5.3 → **v1.5.4** |
| `test:` | **PATCH** 🟢 | v1.5.3 → **v1.5.4** |
| `refactor:` | **PATCH** 🟢 | v1.5.3 → **v1.5.4** |

---

## ✍️ Commit Examples

### Patch Release (v1.0.0 → v1.0.1)
```bash
git commit -m "fix: resolve division by zero error"
git commit -m "docs: update README"
git commit -m "test: add unit tests"
```

### Minor Release (v1.0.0 → v1.1.0)
```bash
git commit -m "feat: add hexadecimal conversion"
git commit -m "feat: implement square root calculation"
```

### Major Release (v1.0.0 → v2.0.0)
```bash
# Option 1: Use ! suffix
git commit -m "feat!: redesign calculator API"

# Option 2: Use BREAKING CHANGE footer
git commit -m "feat: redesign calculator API

BREAKING CHANGE: Calculator.calculate() signature changed"
```

---

## 🔄 Release Process

### Automatic (Recommended)
```
1. Commit with conventional format
2. Create PR to main
3. Merge PR
4. ✅ Release auto-created with correct version!
```

### Manual (For specific versions)
```
1. Go to Actions → Create Version Tag
2. Enter version (e.g., v2.0.0)
3. ✅ Release auto-created!
```

---

## 📦 Each Release Includes

1. **CHANGELOG.md** - Organized by commit type
2. **calculator-{version}.jar** - Versioned build
3. **calculator-latest.jar** - Latest build

---

## 🎯 Quick Commands

### Create Feature (→ v1.1.0)
```bash
git commit -m "feat: add new feature"
```

### Fix Bug (→ v1.0.1)
```bash
git commit -m "fix: resolve bug"
```

### Breaking Change (→ v2.0.0)
```bash
git commit -m "feat!: breaking change"
```

---

## 📊 Version History Example

```
v1.0.0 - Initial release
v1.0.1 - Bug fix
v1.0.2 - Another bug fix
v1.1.0 - New feature added
v1.1.1 - Bug fix in new feature
v1.2.0 - Another new feature
v2.0.0 - Breaking change
v2.0.1 - Hotfix
v2.1.0 - New feature
```

---

## ✅ Duplicate Prevention

The workflow automatically:
- Checks if version already exists
- Skips if duplicate
- Shows warning message
- No errors thrown

---

## 🎉 Benefits

✅ **Industry standard** (Semantic Versioning 2.0.0)
✅ **Fully automated** (based on commit messages)
✅ **Clear communication** (users know impact)
✅ **Professional** (enterprise-quality)
✅ **Zero manual work** (100% automated)

---

**Need more info?** See:
- `.github/SEMANTIC_VERSIONING_GUIDE.md` - Complete guide
- `.github/WORKFLOWS_DOCUMENTATION.md` - All workflows
- [semver.org](https://semver.org/) - Official specification
