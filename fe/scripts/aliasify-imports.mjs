import { promises as fs } from 'fs';
import path from 'path';

const projectRoot = process.cwd();
const srcDir = path.join(projectRoot, 'src');

/**
 * Convert a relative import specifier to an alias-based one using '@/'.
 * For files under src, resolve the target path against the file's directory,
 * then make it relative to src and prefix with '@/'.
 */
function toAliasSpecifier(fromFileAbs, importSpecifier) {
    if (!importSpecifier.startsWith('.')) return null; // skip non-relative
    const fromDir = path.dirname(fromFileAbs);
    const resolved = path.normalize(path.resolve(fromDir, importSpecifier));
    if (!resolved.startsWith(srcDir)) return null; // skip imports outside src
    const relToSrc = path.relative(srcDir, resolved).replace(/\\/g, '/');
    return `@/${relToSrc}`;
}

const exts = new Set(['.js', '.jsx', '.ts', '.tsx']);

async function* walk(dir) {
    const entries = await fs.readdir(dir, { withFileTypes: true });
    for (const entry of entries) {
        // skip node_modules or build outputs if any under src (defensive)
        if (entry.isDirectory() && (entry.name === 'node_modules' || entry.name === 'build' || entry.name === 'dist')) continue;
        const fullPath = path.join(dir, entry.name);
        if (entry.isDirectory()) {
            yield* walk(fullPath);
        } else {
            if (exts.has(path.extname(entry.name))) {
                yield fullPath;
            }
        }
    }
}

const importRegex = /^(\s*import\s+[^'";]+from\s+)["']([^"']+)["']\s*;?/gm;
const sideEffectImportRegex = /^(\s*import\s+)["']([^"']+)["']\s*;?/gm;

async function processFile(file) {
    const original = await fs.readFile(file, 'utf8');
    let changed = original;

    changed = changed.replace(importRegex, (m, pre, spec) => {
        const alias = toAliasSpecifier(file, spec);
        return alias ? `${pre}'${alias}';` : m;
    });

    changed = changed.replace(sideEffectImportRegex, (m, pre, spec) => {
        const alias = toAliasSpecifier(file, spec);
        return alias ? `${pre}'${alias}';` : m;
    });

    if (changed !== original) {
        await fs.writeFile(file, changed, 'utf8');
        return true;
    }
    return false;
}

async function main() {
    let updated = 0;
    for await (const file of walk(srcDir)) {
        const did = await processFile(file);
        if (did) updated += 1;
    }
    // eslint-disable-next-line no-console
    console.log(`Aliasify complete. Files updated: ${updated}`);
}

main().catch((err) => {
    console.error(err);
    process.exit(1);
});


